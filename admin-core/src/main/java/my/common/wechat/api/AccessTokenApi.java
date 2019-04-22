package my.common.wechat.api;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpUtils;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import my.common.wechat.AccessToken;
import my.common.wechat.ApiConfig;
import my.common.wechat.cache.IAccessTokenCache;
import my.common.wechat.utils.RetryUtils;

public class AccessTokenApi {

    // "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    /**
     * 从缓存中获取 access token，如果未取到或者 access token 不可用则先更新再获取
     * @return AccessToken accessToken
     */
    public static AccessToken getAccessToken() {
        ApiConfig ac = ApiConfigKit.getApiConfig();
        AccessToken result = getAvailableAccessToken(ac);
        if (result != null) {
            return result;
        }

        return refreshAccessTokenIfNecessary(ac);
    }

    private static AccessToken getAvailableAccessToken(ApiConfig ac) {
        // 利用 appId 与 accessToken 建立关联，支持多账户
        IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();
        
        String accessTokenJson = accessTokenCache.get(ac.getAppId());
        if (StrUtil.isNotBlank(accessTokenJson)) {
            AccessToken result = new AccessToken(accessTokenJson);
            if (result != null && result.isAvailable()) {
                return result;
            }
        }
        return null;
    }

    /**
     * 直接获取 accessToken 字符串，方便使用
     * @return String accessToken
     */
    public static String getAccessTokenStr() {
        return getAccessToken().getAccessToken();
    }

    /**
     * synchronized 配合再次获取 token 并检测可用性，防止多线程重复刷新 token 值
     */
    private static synchronized AccessToken refreshAccessTokenIfNecessary(ApiConfig ac) {
        AccessToken result = getAvailableAccessToken(ac);
        if (result != null) {
            return result;
        }
        return refreshAccessToken(ac);
    }

    /**
     * 无条件强制更新 access token 值，不再对 cache 中的 token 进行判断
     * @return AccessToken
     */
    public static AccessToken refreshAccessToken() {
        return refreshAccessToken(ApiConfigKit.getApiConfig());
    }

    /**
     * 无条件强制更新 access token 值，不再对 cache 中的 token 进行判断
     * @param ac ApiConfig
     * @return AccessToken
     */
    public static AccessToken refreshAccessToken(ApiConfig ac) {
        String appId = ac.getAppId();
        String appSecret = ac.getAppSecret();
        final Map<String, Object> queryParas = new HashMap<String, Object>();
        queryParas.put("appid", appId);
        queryParas.put("secret", appSecret);
        		
        // 最多三次请求
        AccessToken result = RetryUtils.retryOnException(3, new Callable<AccessToken>() {

            @Override
            public AccessToken call() throws Exception {
            	String json = HttpRequest
            		.get(url)
            		.form(queryParas)
            		.execute()
            		.body();
            	
                return new AccessToken(json);
            }
        });

        // 三次请求如果仍然返回了不可用的 access token 仍然 put 进去，便于上层通过 AccessToken 中的属性判断底层的情况
        if (null != result) {
            // 利用 appId 与 accessToken 建立关联，支持多账户
            IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();
            accessTokenCache.set(ac.getAppId(), result.getCacheJson());
        }
        return result;
    }

}

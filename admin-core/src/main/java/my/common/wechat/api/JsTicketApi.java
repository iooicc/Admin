package my.common.wechat.api;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import my.common.wechat.JsTicket;
import my.common.wechat.cache.IAccessTokenCache;
import my.common.wechat.utils.RetryUtils;

/**
 *
 * 生成签名之前必须先了解一下jsapi_ticket，jsapi_ticket是公众号用于调用微信JS接口的临时票据
 * https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&amp;type=jsapi
 *
 * 微信卡券接口签名凭证api_ticket
 * https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&amp;type=wx_card
 */
public class JsTicketApi {

    private static String apiUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    static IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();

    /**
     * JSApi的类型
     *
     * jsapi: 用于分享等js-api
     *
     * wx_card：用于卡券接口签名凭证api_ticket
     *
     */
    public enum JsApiType {
        jsapi, wx_card
    }

    /**
     *
     * http GET请求获得jsapi_ticket（有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket）
     *
     * @param jsApiType jsApi类型
     * @return JsTicket
     */
    public static JsTicket getTicket(JsApiType jsApiType) {
        String access_token = AccessTokenApi.getAccessTokenStr();
        String appId = ApiConfigKit.getApiConfig().getAppId();
        String key = appId + ':' + jsApiType.name();
        
        final Map<String, Object> queryParas = new HashMap<String, Object>();
        queryParas.put("access_token", access_token);
        queryParas.put("type", jsApiType.name());

        // 2016.07.21修改By L.cm 为了更加方便扩展
        String jsTicketJson = accessTokenCache.get(key);
        JsTicket jsTicket = null;
        if (StrUtil.isNotBlank(jsTicketJson)) {
            jsTicket = new JsTicket(jsTicketJson);
        }
        if (null == jsTicket || !jsTicket.isAvailable()) {
            // 最多三次请求
            jsTicket = RetryUtils.retryOnException(3, new Callable<JsTicket>() {

                @Override
                public JsTicket call() throws Exception {
                	String json = HttpRequest
	            		.get(apiUrl)
	            		.form(queryParas)
	            		.execute()
	            		.body();
                	
                    return new JsTicket(json);
                }

            });

            if (null != jsApiType) {
                accessTokenCache.set(key, jsTicket.getCacheJson());
            }

        }
        return jsTicket;
    }

}

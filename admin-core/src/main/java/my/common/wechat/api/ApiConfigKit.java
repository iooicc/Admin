package my.common.wechat.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hutool.core.util.StrUtil;
import my.common.wechat.ApiConfig;
import my.common.wechat.cache.DefaultAccessTokenCache;
import my.common.wechat.cache.IAccessTokenCache;

public class ApiConfigKit {
    private static final Logger log = LoggerFactory.getLogger(ApiConfigKit.class);

    private static final ThreadLocal<String> TL = new ThreadLocal<String>();

    private static final Map<String, ApiConfig> CFG_MAP = new ConcurrentHashMap<String, ApiConfig>();
    private static final String DEFAULT_CFG_KEY = "_default_cfg_key_";

    // 开发模式将输出消息交互 xml 到控制台
    private static boolean devMode = false;

    public static void setDevMode(boolean devMode) {
        ApiConfigKit.devMode = devMode;
    }

    public static boolean isDevMode() {
        return devMode;
    }

    /**
     * 添加公众号配置，每个appId只需添加一次，相同appId将被覆盖。
     * 第一个添加的将作为默认公众号配置
     * @param apiConfig 公众号配置
     * @return ApiConfig 公众号配置
     */
    public static ApiConfig putApiConfig(ApiConfig apiConfig) {
        if (CFG_MAP.size() == 0) {
            CFG_MAP.put(DEFAULT_CFG_KEY, apiConfig);
        }
        return CFG_MAP.put(apiConfig.getAppId(), apiConfig);
    }

    public static ApiConfig removeApiConfig(ApiConfig apiConfig) {
        return removeApiConfig(apiConfig.getAppId());
    }

    public static ApiConfig removeApiConfig(String appId) {
        return CFG_MAP.remove(appId);
    }

    public static void setThreadLocalAppId(String appId) {
        if (StrUtil.isBlank(appId)) {
            appId = CFG_MAP.get(DEFAULT_CFG_KEY).getAppId();
        }
        TL.set(appId);
    }

    public static void removeThreadLocalAppId() {
        TL.remove();
    }

    public static String getAppId() {
        String appId = TL.get();
        if (StrUtil.isBlank(appId)) {
            appId = CFG_MAP.get(DEFAULT_CFG_KEY).getAppId();
        }
        return appId;
    }

    public static ApiConfig getApiConfig() {
        String appId = getAppId();
        return getApiConfig(appId);
    }

    public static ApiConfig getApiConfig(String appId) {
        log.debug("appId: " + appId);
        ApiConfig cfg = CFG_MAP.get(appId);
        if (cfg == null)
            throw new IllegalStateException("需事先调用 ApiConfigKit.putApiConfig(apiConfig) 将 appId对应的 ApiConfig 对象存入，" +
                    "如JFinalConfig.afterJFinalStart()中调用, 才可以使用 ApiConfigKit.getApiConfig() 系列方法");
        return cfg;
    }

    static IAccessTokenCache accessTokenCache = new DefaultAccessTokenCache();

    public static void setAccessTokenCache(IAccessTokenCache accessTokenCache) {
        ApiConfigKit.accessTokenCache = accessTokenCache;
    }

    public static IAccessTokenCache getAccessTokenCache() {
        return ApiConfigKit.accessTokenCache;
    }
}

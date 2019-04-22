package my.admin.framework.util;

import java.util.List;
import java.util.Map;

import my.admin.core.utils.BaseModelUtil;
import my.admin.framework.service.ConfigService;
import my.admin.model.SysConfig;
import my.common.cache.CacheUtil;
import my.common.utils.SpringUtil;

public class ConfigUtil {
	
	private static ConfigService configService = null;
	
	public static void clearCache() {
		CacheUtil.remove("configMap");
	}
	
	public static synchronized String getConfigString(String key) {
		SysConfig config = getConfig(key);
		if(config==null) {
			return "";
		}
		return config.getConfigValue();
	}
	
	public static synchronized SysConfig getConfig(String key) {
		
		if(configService==null) {
			configService = (ConfigService) SpringUtil.getBean(ConfigService.class);
			if(configService==null) {
				return null;
			}
		}

		Map<String, SysConfig> configMap = (Map) CacheUtil.get("configMap");
		if(configMap==null) {
			List<SysConfig> list = configService.all();
			configMap = BaseModelUtil.getMap_Map(list, "configKey");
			CacheUtil.put("configMap", configMap);
		}
		
		SysConfig obj = configMap.get(key);
		return obj;
	}

}

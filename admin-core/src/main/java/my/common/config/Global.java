package my.common.config;

import my.common.io.PropertiesUtil;

public class Global {
	private static final Global INSTANCE = new Global();
	
	public static Global getInstance() {
		return INSTANCE;
	}
	
	public static <T> T get(String key) {
		return PropertiesUtil.getInstance().get(key);
	}
	
	public static String getProperty(String key) {
		return PropertiesUtil.getInstance().getProperty(key);
	}
	
	public static String getProperty(String key, String defaultValue) {
		String value = PropertiesUtil.getInstance().getProperty(key);
		if(value==null) {
			value = defaultValue;
		}
		return value;
	}
}

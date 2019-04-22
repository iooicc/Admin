package my.common.io;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.Resource;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

public class PropertiesUtil {
	
	// 默认加载的文件，可通过继承覆盖（若有相同Key，优先加载后面的）
	public static final String[] DEFAULT_CONFIG_FILE = new String[] {
		"classpath:config/application.yml"
		,"classpath:application.yml"
	};
	
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	
	private final Properties properties = new Properties();
	
	/**
	 * 当前类的实例持有者（静态内部类，延迟加载，懒汉式，线程安全的单例模式）
	 */
	private static final class PropertiesLoaderHolder {
		private static PropertiesUtil INSTANCE;
		static {
			releadInstance();
		}
		public static void releadInstance(){
			// 获取平台及模块相关的配置文件
			Set<String> configSet = new LinkedHashSet<String>();
			Resource[] resources = ResourceUtil.getResources("classpath*:/config/*.*");
			for(Resource resource : resources){
				configSet.add("classpath:config/"+resource.getFilename());
			}

			// 获取全局设置默认的配置文件（以下是支持环境配置的属性文件）
			Set<String> set = new LinkedHashSet<String>();
			for (String configFile : DEFAULT_CONFIG_FILE){
				set.add(configFile);
			}
			
			// 获取 spring.profiles.active 活动环境名称的配置文件
			String[] configFiles = set.toArray(new String[set.size()]);
			String profiles = System.getProperty("spring.profiles.active");
			if (StrUtil.isBlank(profiles)){
				PropertiesUtil propsTemp = new PropertiesUtil(configFiles);
				profiles = propsTemp.getProperty("spring.profiles.active");
			}
			for (String location : configFiles){
				configSet.add(location);
				if (StrUtil.isNotBlank(profiles) && !StrUtil.equals(profiles, "default")){
					if (location.endsWith(".properties")){
						configSet.add(StrUtil.subBefore(location, ".properties", false)
								+ "-" + profiles + ".properties");
					} else if (location.endsWith(".yml")){
						configSet.add(StrUtil.subBefore(location, ".yml", false)
								+ "-" + profiles + ".yml");
					}
				}
			}
			configFiles = configSet.toArray(new String[configSet.size()]);
			logger.debug("Loading jeesite config: {}", (Object)configFiles);
			INSTANCE = new PropertiesUtil(configFiles);
			
			System.out.println(">>>>>>");
			System.out.println(JSONUtil.toJsonPrettyStr(INSTANCE.getProperties()));
			System.out.println("<<<<<<");
		}
	}
	
	/**
	 * 载入多个文件，路径使用Spring Resource格式，相同的属性在最后载入的文件中的值将会覆盖之前的值。
	 */
	public PropertiesUtil(String... configFiles) {
		for (String location : configFiles) {
			try {
				Resource resource = ResourceUtil.getResource(location);
				if (resource.exists()){
        			if (location.endsWith(".properties")){
        				InputStreamReader is = null;
        				try {
	    					is = new InputStreamReader(resource.getInputStream(), "UTF-8");
	    					properties.load(is);
	        			} catch (IOException ex) {
	            			logger.error("Load " + location + " failure. ", ex);
	        			} finally {
	        				IoUtil.close(is);
	        			}
        			} else if (location.endsWith(".yml")){
        				YamlPropertiesFactoryBean bean = new YamlPropertiesFactoryBean();
        				bean.setResources(resource);
        				for (Map.Entry<Object,Object> entry : bean.getObject().entrySet()){
        					properties.put(entry.getKey(), entry.getValue());
        				}
        			}
				}
			} catch (Exception e) {
    			logger.error("Load " + location + " failure. ", e);
			}
		}
		// 存储当前加载的配置文件路径和名称
		properties.setProperty("configFiles", StrUtil.join(",", configFiles));
	}
	
	/**
	 * 获取当前加载的属性
	 */
	public Properties getProperties() {
		return properties;
	}
	
	/**
	 * 当前类实例
	 */
	public static PropertiesUtil getInstance(){
		return PropertiesLoaderHolder.INSTANCE;
	}
	
	/**
	 * 重新加载实例（重新实例化，以重新加载属性文件数据）
	 */
	public static void releadInstance(){
		PropertiesLoaderHolder.releadInstance();
	}
	
	// 正则表达式预编译
	private static Pattern p1 = Pattern.compile("\\$\\{.*?\\}");
	
	public <T> T get(String key) {
		Object value = properties.get(key);
		return (T) value;
	}

	/**
	 * 获取属性值，取不到从System.getProperty()获取，都取不到返回null
	 */
	public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value != null){
        	// 支持嵌套取值的问题  key=${xx}/yy
	    	Matcher m = p1.matcher(value);
	        while(m.find()) {
	            String g = m.group();
	            String keyChild = g.replaceAll("\\$\\{", "").replaceAll("\\}", "");
	            value = StrUtil.replace(value, g, getProperty(keyChild));
	        }
	        return value;
	    }else{
	    	String systemProperty = System.getProperty(key);
			if (systemProperty != null) {
				return systemProperty;
			}
	    }
		return null;
	}

	/**
	 * 取出String类型的Property，但以System的Property优先，如果都为null则返回defaultValue值
	 */
	public String getProperty(String key, String defaultValue) {
		String value = getProperty(key);
		return value != null ? value : defaultValue;
	}
}

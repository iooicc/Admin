package my.admin.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import my.common.config.Global;

@Configuration
public class MyBeetlConfig {
	
	@Value("${beetl.templatesPath:templates}")
	String templatesPath;//模板根目录 ，比如 "templates"

//	@Bean(name = "beetlConfig")
//	public BeetlGroupUtilConfiguration beetlGroupUtilConfiguration() {
//		BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
//		// 获取Spring Boot 的ClassLoader
//		ClassLoader loader = Thread.currentThread().getContextClassLoader();
//		if (loader == null) {
//			loader = MyBeetlConfig.class.getClassLoader();
//		}
//		Properties extProperties = new Properties();
//		beetlGroupUtilConfiguration.setConfigProperties(extProperties);// 额外的配置，可以覆盖默认配置，一般不需要
//		ClasspathResourceLoader cploder = new ClasspathResourceLoader(loader, templatesPath);
//		beetlGroupUtilConfiguration.setResourceLoader(cploder);
//		beetlGroupUtilConfiguration.init();
//		// 如果使用了优化编译器，涉及到字节码操作，需要添加ClassLoader
//		beetlGroupUtilConfiguration.getGroupTemplate().setClassLoader(loader);
//		
//		GroupTemplate groupTemplate = beetlGroupUtilConfiguration.getGroupTemplate();
//		Map<String, Object> sharedVars = new LinkedHashMap<String, Object>();
//		sharedVars.put("version", "0.1");
//		sharedVars.put("ctx", Global.getProperty("server.context-path", "")+Global.getProperty("project.context-path"));
//		
//		groupTemplate.setSharedVars(sharedVars);
//		
//		
//		return beetlGroupUtilConfiguration;
//
//	}

	@Bean(name = "beetlViewResolver")
	public BeetlSpringViewResolver beetlSpringViewResolver(
			@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
		BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
		beetlSpringViewResolver.setSuffix(".html");
		beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
		beetlSpringViewResolver.setOrder(0);
		beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
		
		GroupTemplate groupTemplate = beetlGroupUtilConfiguration.getGroupTemplate();
		Map<String, Object> sharedVars = new LinkedHashMap<String, Object>();
		sharedVars.put("version", "0.1");
		sharedVars.put("ctx", Global.getProperty("server.context-path", "")+Global.getProperty("project.context-path"));
		groupTemplate.setSharedVars(sharedVars);
		
		return beetlSpringViewResolver;
	}

	@Bean
	public BeetlSqlDataSource beetlSqlDataSource(@Qualifier("dataSource") DataSource dataSource) {
		BeetlSqlDataSource source = new BeetlSqlDataSource();
		source.setMasterSource(dataSource);
		return source;
	}
}

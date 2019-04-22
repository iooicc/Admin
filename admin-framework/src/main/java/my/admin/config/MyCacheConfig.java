package my.admin.config;

import org.apache.shiro.cache.CacheManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import my.common.shiro.cache.ShiroCacheManager;
import my.common.config.Global;

@Configuration
@EnableCaching
public class MyCacheConfig {
	
	@Bean(name = {"ehCacheManager"})
	public EhCacheManagerFactoryBean ehCacheManager(Environment environment) {
		EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
		bean.setConfigLocation(new ClassPathResource(Global.getProperty("spring.cache.ehcache.config")));
		return bean;
	}
	
	@Bean(name = {"shiroCacheManager"})
	@ConditionalOnMissingBean({CacheManager.class})
	public CacheManager shiroCacheManager(EhCacheManagerFactoryBean ehCacheManager) {
		ShiroCacheManager bean = new ShiroCacheManager(ehCacheManager.getObject());
		return bean;
	}

}

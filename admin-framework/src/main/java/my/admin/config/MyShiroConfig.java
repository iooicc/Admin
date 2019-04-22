package my.admin.config;

import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import my.common.shiro.config.FilterChainDefinitionMap;
import my.common.shiro.filter.FormAuthenticationFilter;
import my.common.shiro.realm.AuthorizingRealm;
import my.common.shiro.session.SessionDAO;
import my.common.config.Global;

@Configuration
public class MyShiroConfig {
	
//	@Bean(name={"sessionDAO"})
//	@ConditionalOnMissingBean({SessionDAO.class})
//	@ConditionalOnProperty(name={"spring.redis.enabled"}, havingValue="true", matchIfMissing=false)
//	public SessionDAO sessionDAORedis() {
//		// TODO
//		return null;
//	}
	
//	@Bean(name={"sessionDAO"})
//	@ConditionalOnMissingBean({SessionDAO.class})
//	@ConditionalOnProperty(name={"spring.redis.enabled"}, havingValue="false", matchIfMissing=true)
//	public SessionDAO sessionDAO(CacheManager shiroCacheManager) {
//		return null;
//	}
	
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher bean = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        bean.setHashAlgorithmName("MD5");
        //加密次数
        bean.setHashIterations(1);
        bean.setStoredCredentialsHexEncoded(true);
        return bean;
    }
	
	/**
	 * 系统安全认证实现类
	 */
	@Bean
	public AuthorizingRealm authorizingRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
		AuthorizingRealm bean = new AuthorizingRealm();
		bean.setCredentialsMatcher(hashedCredentialsMatcher);
		return bean;
	}
	
	/**
	 * 定义Shiro安全管理配置
	 */
	@Bean
	public WebSecurityManager securityManager(
		AuthorizingRealm authorizingRealm
		,CacheManager shiroCacheManager
	) {
		DefaultWebSecurityManager bean = new DefaultWebSecurityManager();
		bean.setRealm(authorizingRealm);
		bean.setCacheManager(shiroCacheManager);
		return bean;
	}
	
	/**
	 * Shiro认证过滤器
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(
		WebSecurityManager securityManager
		, AuthorizingRealm authorizingRealm
//		, CasAuthorizingRealm casAuthorizingRealm
	) {
		
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager);
		bean.setLoginUrl("/login");
		bean.setSuccessUrl("/");
		Map<String, Filter> filters = bean.getFilters();
//		filters.put("cas", shiroCasFilter(casAuthorizingRealm));
		filters.put("authc", shiroAuthcFilter(authorizingRealm));
//		filters.put("logout", shiroLogoutFilter(authorizingRealm));
//		filters.put("perms", shiroPermsFilter());
//		filters.put("roles", shiroRolesFilter());
//		filters.put("user", shiroUserFilter());
		FilterChainDefinitionMap chains = new FilterChainDefinitionMap();
		chains.setFilterChainDefinitions(Global.getProperty("shiro.filterChainDefinitions"));
		chains.setDefaultFilterChainDefinitions(Global.getProperty("shiro.defaultFilterChainDefinitions"));
		bean.setFilterChainDefinitionMap(chains.getObject());
		return bean;
	}
	
	/**
	 * Form登录过滤器
	 */
	private FormAuthenticationFilter shiroAuthcFilter(AuthorizingRealm authorizingRealm) {
		FormAuthenticationFilter bean = new FormAuthenticationFilter();
		bean.setAuthorizingRealm(authorizingRealm);
		return bean;
	}
}

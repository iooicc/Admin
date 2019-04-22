package my.admin.config;

import java.util.Collections;
import java.util.List;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import my.admin.framework.interceptor.WechatInterceptor;
import my.common.config.Global;

@Configuration
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	SQLManager sqlManager;
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(!registry.hasMappingForPattern("/static/**")){
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
        
        registry.addResourceHandler("/files/**").addResourceLocations("file:///" + Global.getProperty("project.upload.savePath"));
        
        super.addResourceHandlers(registry);
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		
		addWechaInterceptor(registry);
	}

	private void addWechaInterceptor(InterceptorRegistry registry) {
		Boolean enabled = Global.get("project.wechat.enabled");
		if(enabled!=null && enabled) {
			String patterns = Global.getProperty("project.wechat.patterns");
			List<String> list = StrUtil.splitTrim(patterns, StrUtil.LF);
			
			if(list!=null && list.size()>0) {
				String[] array = new String[list.size()];
				list.toArray(array);
				registry.addInterceptor(new WechatInterceptor(sqlManager)).addPathPatterns(
					array
				);
			}
		}
	}
}

package my.admin.framework.interceptor;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beetl.sql.core.SQLManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.hutool.core.util.StrUtil;
import my.admin.framework.util.UserUtil;
import my.admin.model.SysUser;
import my.common.config.Global;
import my.common.web.http.ServletUtil;
import my.common.wechat.ApiConfig;
import my.common.wechat.SnsAccessToken;
import my.common.wechat.api.ApiConfigKit;
import my.common.wechat.api.SnsAccessTokenApi;

public class WechatInterceptor implements HandlerInterceptor {
	
	private SQLManager sqlManager;
	
	public WechatInterceptor(SQLManager sqlManager) {
		this.sqlManager = sqlManager;
	}
	
	private void getApiConfig() {
		ApiConfig apiConfig = new ApiConfig(
			Global.getProperty("project.wechat.token")
    		, Global.getProperty("project.wechat.appId")
    		, Global.getProperty("project.wechat.appSecret")
    	);
		
		ApiConfigKit.putApiConfig(apiConfig);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		SysUser user = UserUtil.getUser();
		if(user!=null
			&& StrUtil.isBlank(user.getWxOpenid())
		) {
			
			String code = request.getParameter("code");
			String redirect_uri = ServletUtil.getRequestURL();
			
			if(code==null) {
				String redirect = String.format(
					"%s?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect"
					, Global.getProperty("project.wechat.userAuthorizationUri")
					, Global.getProperty("project.wechat.appId")
					, URLEncoder.encode(redirect_uri, "UTF-8")
					, "snsapi_userinfo"//snsapi_base
					, "1"
				);
				
				ServletUtil.redirect(redirect);
				return false;
			} else {
				getApiConfig();
				
				ApiConfig apiConfig = ApiConfigKit.getApiConfig(); 
				String appId = apiConfig.getAppId();
				String secret = apiConfig.getAppSecret();
				SnsAccessToken snsAccessToken = SnsAccessTokenApi.getSnsAccessToken(appId, secret, code);
				if(snsAccessToken.isAvailable()) {
					String token = snsAccessToken.getAccessToken();
					String openid = snsAccessToken.getOpenid();
					
					Map<String, Object> paras = new HashMap<String, Object>();
					paras.put("wxOpenid", openid);
					paras.put("userCode", user.getUserCode());
					sqlManager.executeUpdate(
						"update sys_user"
						+" set wx_openid=#wxOpenid#"
						+" where user_code=#userCode#"
						, paras
					);
					
					//
					user.setWxOpenid(openid);
				} else {
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}

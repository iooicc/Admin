package my.common.shiro.filter;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.util.WebUtils;

import cn.hutool.core.util.StrUtil;
import my.common.shiro.authc.FormToken;
import my.common.codec.EncodeUtils;
import my.common.web.http.ServletUtil;

public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

	public static final String DEFAULT_CAPTCHA_PARAM = "validCode"; // 验证码
	public static final String DEFAULT_MESSAGE_PARAM = "message"; // 登录返回消息
	public static final String DEFAULT_REMEMBER_USERCODE_PARAM = "rememberUserCode"; // 记住用户名
	
	private Cookie rememberUserCodeCookie; // 记住用户名Cookie
	
	private AuthorizingRealm authorizingRealm; // 安全认证类
	public void setAuthorizingRealm(AuthorizingRealm authorizingRealm) {
		this.authorizingRealm = authorizingRealm;
	}
	
	/**
	 * 构造方法
	 */
	public FormAuthenticationFilter() {
		super();
		rememberUserCodeCookie = new SimpleCookie(DEFAULT_REMEMBER_USERCODE_PARAM);
		rememberUserCodeCookie.setHttpOnly(true);
        rememberUserCodeCookie.setMaxAge(Cookie.ONE_YEAR);
	}
	
	/**
	 * 创建登录授权令牌
	 */
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request, response);	// 用户名
		String password = getPassword(request);				// 登录密码
		boolean rememberMe = isRememberMe(request);			// 记住我（记住密码）
		String host = getHost(request);						// 登录主机
		String captcha = getCaptcha(request);				// 登录验证码
		Map<String, Object> paramMap = ServletUtil.getExtParams(request);	// 登录附加参数
		return new FormToken(username, password.toCharArray(), rememberMe, host, captcha, paramMap);
	}
	
	/**
	 * 获取登录用户名
	 */
	protected String getUsername(ServletRequest request, ServletResponse response) {
		String username = super.getUsername(request);
		if (StrUtil.isBlank(username)){
			Object oUsername = request.getAttribute(getUsernameParam());
			if(oUsername==null) {
				username = "";
			} else {
				username = oUsername.toString();
			}
		}

		// 登录成功后，判断是否需要记住用户名
		if (WebUtils.isTrue(request, DEFAULT_REMEMBER_USERCODE_PARAM)) {
			rememberUserCodeCookie.setValue(EncodeUtils.xssFilter(username));
			rememberUserCodeCookie.saveTo((HttpServletRequest)request, (HttpServletResponse)response);
		} else {
			rememberUserCodeCookie.removeFrom((HttpServletRequest)request, (HttpServletResponse)response);
		}
		return username;
	}
	
	/**
	 * 获取登录密码
	 */
	@Override
	protected String getPassword(ServletRequest request) {
		String password = super.getPassword(request);
		if (StrUtil.isBlank(password)){
			Object oPassword = request.getAttribute(getPasswordParam());
			if(oPassword==null) {
				password = "";
			} else {
				password = oPassword.toString();
			}
		}
		// 登录密码解密（解决登录密码明文传输安全问题）
		return password;
	}
	
	/**
	 * 获取登录验证码
	 */
	protected String getCaptcha(ServletRequest request) {
		String captcha = WebUtils.getCleanParam(request, DEFAULT_CAPTCHA_PARAM);
		if (StrUtil.isBlank(captcha)){
			Object oCaptcha = request.getAttribute(DEFAULT_CAPTCHA_PARAM);
			if(oCaptcha==null) {
				captcha = "";
			} else {
				captcha = oCaptcha.toString();
			}
		}
		return captcha;
	}
	
	/**
	 * 登录成功调用事件
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {

		if (ServletUtil.isAjaxRequest((HttpServletRequest) request)) {
			// 登录操作如果是Ajax操作，直接返回登录信息字符串。
			request.getRequestDispatcher(getSuccessUrl()).forward(request, response); // AJAX不支持Redirect改用Forward
		} else {
			// 登录成功直接返回到首页
			String url = request.getParameter("__url");
			if (StrUtil.isNotBlank(url)) {
				WebUtils.issueRedirect(request, response, url, null, true);
			} else {
				WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
			}
		}
		return false;
	}
	
	/**
	 * 登录失败调用事件
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
		String className = e.getClass().getName(), message = "";
		if (IncorrectCredentialsException.class.getName().equals(className) || UnknownAccountException.class.getName().equals(className)) {
			message = "账号或密码错误，请重试.";
		} else if (e.getMessage() != null && StrUtil.startWith(e.getMessage(), "msg:")) {
			message = StrUtil.replace(e.getMessage(), "msg:", "");
		} else {
			message = "登录异常.";
		}
		request.setAttribute(getFailureKeyAttribute(), className);
		request.setAttribute(DEFAULT_MESSAGE_PARAM, message);
		return true;
	}
}

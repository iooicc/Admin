package my.admin.framework.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.hutool.core.util.StrUtil;
import my.common.shiro.filter.FormAuthenticationFilter;
import my.common.shiro.realm.LoginInfo;
import my.admin.core.controller.BaseController;
import my.admin.framework.util.UserUtil;
import my.admin.model.SysUser;
import my.common.web.http.ServletUtil;

@Controller
@RequestMapping({ "/" })
public class LoginController extends  BaseController {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 地址中如果包含JSESSIONID，则跳转一次，去掉JSESSIONID信息。
		if (StrUtil.containsIgnoreCase(request.getRequestURI(), ";JSESSIONID=")){
			String queryString = request.getQueryString();
			queryString = queryString == null ? "" : "?" + queryString;
			return REDIRECT + "/login" + queryString;
		}
		
		LoginInfo user = UserUtil.getLoginInfo();
		
		// 如果已经登录，则跳转到管理首页
		if(user != null){
			String queryString = request.getQueryString();
			queryString = queryString == null ? "" : "?" + queryString;
			String indexUrl = "/" + queryString;
			if (ServletUtil.isAjaxRequest(request)){
				try {
					request.getRequestDispatcher(indexUrl).forward(request, response); // AJAX不支持Redirect改用Forward
				} catch (Exception ex) {
					logger.error(ex.getMessage(), ex);
				}
				return null;
			}
			return REDIRECT + indexUrl;
		}
		
		model.addAttribute("isValidCodeLogin", false);
		
		// 如果是Ajax请求，返回Json字符串。
		if (ServletUtil.isAjaxRequest((HttpServletRequest)request)){
			model.addAttribute("result", "login");
			model.addAttribute("message", "请先登录");
			return ServletUtil.renderObject(response, model);
		}
		return "modules/framework/login";
	}
	
	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginFailure(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		LoginInfo user = UserUtil.getLoginInfo();
		// 如果已经登录，则跳转到管理首页
		if(user != null){
			String queryString = request.getQueryString();
			queryString = queryString == null ? "" : "?" + queryString;
			String indexUrl = "/" + queryString;
			if (ServletUtil.isAjaxRequest(request)){
				try {
					request.getRequestDispatcher(indexUrl).forward(request, response); // AJAX不支持Redirect改用Forward
				} catch (Exception ex) {
					logger.error(ex.getMessage(), ex);
				}
				return null;
			}
			return REDIRECT + indexUrl;
		}
		
		String exception = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String message = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
		
		model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
		
		// 登录操作如果是Ajax操作，直接返回登录信息字符串。
		if (ServletUtil.isAjaxRequest(request)){
			model.addAttribute("result", "false");
			return ServletUtil.renderObject(response, model);
		}
		
		
		return "modules/framework/login";
	}
	
	@RequestMapping({"/", "/index"})
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 地址中如果包含JSESSIONID，则跳转一次，去掉JSESSIONID信息。
		if (StrUtil.containsIgnoreCase(request.getRequestURI(), ";JSESSIONID=")){
			String queryString = request.getQueryString();
			queryString = queryString == null ? "" : "?" + queryString;
			return REDIRECT + "/index" + queryString;
		}
		
		// 验证下用户权限，以便调用doGetAuthorizationInfo方法，保存单点登录登出句柄
//		if (!SecurityUtils.getSubject().isPermitted("user")){
//			return REDIRECT + "/login";
//		}
		
		LoginInfo loginInfo = UserUtil.getLoginInfo();
		
		// 未加载shiro模块时会为空，直接访问则提示操作权限不足。
		if(loginInfo == null){
			UserUtil.getSubject().logout();
			return REDIRECT + "/login";
		}
		
		// 当前用户对象信息
		SysUser user = UserUtil.getUser();
		if (user == null){
			UserUtil.getSubject().logout();
			return REDIRECT + "/login";
		}
		model.addAttribute("user", user); // 设置当前用户信息
		
		//获取当前会话对象
		Session session = UserUtil.getSession();
		
		if (ServletUtil.isAjaxRequest(request)){
			// 登录操作如果是Ajax操作，直接返回登录信息字符串。
			model.addAttribute("result", "true");
			// 如果是登录，则返回登录成功信息，否则返回获取成功信息
			model.addAttribute("sessionid", (String)session.getId());
			return ServletUtil.renderObject(response, model);
		}
		
		return "modules/framework/index";
	}
}

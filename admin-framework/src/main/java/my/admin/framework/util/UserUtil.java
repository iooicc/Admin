package my.admin.framework.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import cn.hutool.core.util.StrUtil;
import my.common.shiro.realm.LoginInfo;
import my.admin.framework.service.MenuService;
import my.admin.model.SysMenu;
import my.admin.model.SysUser;
import my.common.utils.SpringUtil;

public class UserUtil {
	
	public static Session getSession() {
		Subject subject = null;
		Session session = null;
		if((subject=getSubject())!=null
			&& (session=subject.getSession(false))!=null
		) {
			return session;
		}
		
		return null;
	}
	
	public static Object getCache(String key) {
		return getSession().getAttribute(key);
	}
	
	public static void putCache(String key, Object value) {
		getSession().setAttribute(key, value);
	}
	
	
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}
	
	public static List<SysMenu> getMenuTree() {
		List<SysMenu> menus = getMenuList();
		return menus;
	}
	
	public static List<SysMenu> getMenuList() {
		return getMenuListByParentCode((String) null);
	}
	
	public static List<SysMenu> getMenuListByParentCode(String parentCode) {
		List<SysMenu> menus = new ArrayList<SysMenu>();
		
		MenuService menuService = SpringUtil.getBean(MenuService.class);
		
		if((menus = (List) getCache("menuList"))!=null) {
			return menus;
		}
		
		SysUser user = getUser();
		String userCode = user.getUserCode();
		
		menus = menuService.getList_ModuleMenus(userCode);
		putCache("menuList", menus);
		
		return menus;
	}
	
	public static LoginInfo getLoginInfo() {
		// TODO
		try {
			SysUser user = (SysUser) getSubject().getPrincipal();
			if(user==null) {
				return null;
			}
			
			LoginInfo info = new LoginInfo(user, null);
			return info;
		} catch (UnavailableSecurityManagerException e) {
			e.printStackTrace();
		} catch (InvalidSessionException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static SysUser getUser() {
		// TODO
		SysUser user = (SysUser) getSubject().getPrincipal();
		return user;
	}
	
	public static SysUser get(String userCode) {
		// TODO
		if (StrUtil.isBlank(userCode)) {
			return null;
		}
		return null;
	}
}

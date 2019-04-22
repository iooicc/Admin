package my.admin.framework.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import my.admin.core.service.BaseServiceImpl;
import my.admin.core.utils.BaseModelUtil;
import my.admin.framework.dao.MenuDao;
import my.admin.model.SysMenu;
import my.admin.model.SysRoleMenu;
import my.admin.model.SysUserRole;
import my.common.tree.TreeWalker;

@Service
public class MenuServiceImpl extends BaseServiceImpl<SysMenu, MenuDao> implements MenuService {
	
	@Override
	public SysMenu single(String menuCode) {
		
		if("0".equals(menuCode)) {
			SysMenu root = new SysMenu();
			root.setMenuCode("0");
			root.setTreeLevel(0);
			return root;
		}
		
		SysMenu row = super.single(menuCode);
		String parentCode = null;
		if(row==null || (parentCode=row.getParentCode())==null) {
			return null;
		}
		
		SysMenu parent = super.single(parentCode);
		row.setParent(parent);
		
		return row;
	}
	
	@Override
	public List<SysMenu> getList_ModuleMenus(String userCode) {
		
		Map<String, Object> paras = MapUtil.newHashMap();
		
		List<SysMenu> menus = new ArrayList<SysMenu>();
		List<SysRoleMenu> roleMenuList = null;
		List<SysUserRole> userRoleList = null;
		
		paras.put("userCode", userCode);
		userRoleList = execute(
			"select"
			+" t1.role_code"
			+" from sys_user_role t1"
			+" where t1.user_code=#userCode#"
			,SysUserRole.class
			,paras
		);
		
		if(userRoleList==null || userRoleList.size()==0) {
			return menus;
		}
		
		String in = BaseModelUtil.getIn(userRoleList, "roleCode");
		if(StrUtil.isBlank(in)) {
			return menus;
		}
		
		roleMenuList = execute(
			"select"
			+" t1.menu_code"
			+" from sys_role_menu t1"
			+" where t1.role_code in "+in
			,SysRoleMenu.class
			,paras
		);
		
		if(roleMenuList==null || roleMenuList.size()==0) {
			return menus;
		}
		
		in = BaseModelUtil.getIn(roleMenuList, "menuCode");
		if(StrUtil.isBlank(in)) {
			return menus;
		}
		
		menus = execute(
			"select"
			+" t1.*"
			+" from sys_menu t1"
			+" where t1.menu_code in "+in
			+" and t1.menu_type = 1"// 菜单类型（1菜单 2权限 3开发）
			,SysMenu.class
			,paras
		);
		
		if(menus==null||menus.size()==0) {
			return menus;
		}
		
		TreeWalker<String> walker = new TreeWalker<String>(menus);
		SysMenu root = new SysMenu();
		root.setMenuCode("0");
		walker.getTree(root, null);
		
		menus = root.getChildren();
		if(menus==null||menus.size()==0) {
			return menus;
		}
		
		return menus;
	}
}
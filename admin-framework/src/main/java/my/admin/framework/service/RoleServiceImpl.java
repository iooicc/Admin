package my.admin.framework.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import my.admin.core.service.BaseServiceImpl;
import my.admin.framework.dao.RoleDao;
import my.admin.model.SysRole;
import my.admin.model.SysRoleDataScope;
import my.admin.model.SysRoleMenu;
import my.admin.model.SysUserRole;

@Service
public class RoleServiceImpl extends BaseServiceImpl<SysRole, RoleDao> implements RoleService {
	
	@Transactional(readOnly = false)
	@Override
	public void saveAuth(SysRole role) {
		Map<String, Object> paras = new HashMap<String, Object>();
		
		paras.put("roleCode", role.getRoleCode());
		executeUpdate(
			"delete from sys_role_menu where role_code=#roleCode#"
			,paras
		);
		List<SysRoleMenu> roleMenuList = role.getRoleMenuList();
		insertBatch(SysRoleMenu.class, roleMenuList);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void saveAuthUser(SysRole role) {
		Map<String, Object> paras = new HashMap<String, Object>();
		
		String roleCode = role.getRoleCode();
		if(!StrUtil.isBlank(roleCode)) {
			paras.put("roleCode", role.getRoleCode());
			executeUpdate(
				"delete from sys_user_role where role_code=#roleCode#"
				,paras
			);
			List<SysUserRole> userRoleList = role.getUserRoleList();
			insertBatch(SysUserRole.class, userRoleList);
		}
	}

	@Transactional(readOnly = false)
	@Override
	public void saveAuthDataScope(SysRole role) {
		Map<String, Object> paras = new HashMap<String, Object>();
		
		String roleCode = role.getRoleCode();
		if(!StrUtil.isBlank(roleCode)) {
			paras.put("roleCode", role.getRoleCode());
			executeUpdate(
				"delete from sys_role_data_scope where role_code=#roleCode#"
				,paras
			);
			List<SysRoleDataScope> userRoleList = role.getRoleDataScopeList();
			insertBatch(SysRoleDataScope.class, userRoleList);
		}
	}
	
	public List<SysRole> findListByUserCode(String userCode) {
		Map<String, Object> paras = MapUtil.newHashMap();
		
		paras.put("userCode", userCode);
		List<SysRole> list = execute(
			"select"
			+" t1.*"
			+" from sys_role t1, sys_user_role t2"
			+" where t1.role_code=t2.role_code"
			+" and t2.user_code=#userCode#"
			,paras
		);
		return list;
	}

	
	@Override
	@Transactional(readOnly = false)
	public void deleteAuthUser(String roleCode, String userCodes) {
       Map< String, Object> paras=new ConcurrentHashMap<>();
       paras.put("roleCode", roleCode);
       executeUpdate(
				"delete from sys_user_role "
				+ " where role_code=#roleCode# "
				+ " and user_code in ("+userCodes+")"
				,paras
			);      
		
	}
}
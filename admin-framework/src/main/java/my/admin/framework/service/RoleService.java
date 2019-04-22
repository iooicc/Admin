package my.admin.framework.service;

import java.util.List;

import my.admin.core.service.BaseService;
import my.admin.model.SysRole;

public interface RoleService extends BaseService<SysRole> {
	void saveAuth(SysRole role);
	void saveAuthUser(SysRole role);
	void saveAuthDataScope(SysRole role);
	
	List<SysRole> findListByUserCode(String userCode);
	
	void deleteAuthUser(String roleCode,String userCodes);
	
}

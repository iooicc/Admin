package my.admin.framework.service;

import org.beetl.sql.core.engine.PageQuery;

import my.admin.core.service.BaseService;
import my.admin.model.SysUser;
import my.common.beans.Result;

public interface UserService extends BaseService<SysUser>, my.common.shiro.realm.UserService {
	SysUser single(String userCode);
	void page(PageQuery page);
	
	boolean checkLoginCode(String oldLoginCode, String loginCode);
	Result register_save(SysUser row);
	Result save(SysUser row);
	
	boolean validatePassword(String password, String userCode);
	void updatePassword(String password, String userCode);
	
	
}

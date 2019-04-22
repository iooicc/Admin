package my.admin.framework.service;

import my.admin.core.service.BaseService;
import my.admin.model.SysAdmin;

public interface AdminService extends BaseService<SysAdmin> {
	boolean checkLoginCode(String oldLoginCode, String loginCode);
	void save(SysAdmin row);
}

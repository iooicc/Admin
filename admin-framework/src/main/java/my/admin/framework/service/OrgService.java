package my.admin.framework.service;

import my.admin.core.service.BaseService;
import my.admin.model.SysOrg;

public interface OrgService extends BaseService<SysOrg> {
	
	SysOrg single(String menuCode);

}

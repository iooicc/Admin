package my.admin.framework.service;

import java.util.List;

import my.admin.core.service.BaseService;
import my.admin.model.SysMenu;

public interface MenuService extends BaseService<SysMenu> {
	SysMenu single(String menuCode);
	List<SysMenu> getList_ModuleMenus(String userCode);
}

package my.admin.framework.service;

import java.util.List;

import my.admin.core.service.BaseService;
import my.admin.model.Area;

public interface AreaService extends BaseService<Area> {
	Area single(String menuCode);
}

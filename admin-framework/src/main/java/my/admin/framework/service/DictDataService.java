package my.admin.framework.service;

import java.util.List;

import my.admin.core.service.BaseService;
import my.admin.model.SysDictData;

public interface DictDataService extends BaseService<SysDictData> {
	List<SysDictData> all();
}

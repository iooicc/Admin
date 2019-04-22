package my.admin.framework.service;

import java.util.List;

import org.springframework.stereotype.Service;

import my.admin.core.service.BaseServiceImpl;
import my.admin.framework.dao.DictDataDao;
import my.admin.model.SysDictData;

@Service
public class DictDataServiceImpl extends BaseServiceImpl<SysDictData, DictDataDao> implements DictDataService {
	
	public List<SysDictData> all() {
		SysDictData condition = new SysDictData();
		
		List<SysDictData> list = query().orderBy("tree_sort").select();
		return list;
	}
}

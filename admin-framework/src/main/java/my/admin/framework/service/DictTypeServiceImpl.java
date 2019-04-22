package my.admin.framework.service;

import org.springframework.stereotype.Service;

import my.admin.core.service.BaseServiceImpl;
import my.admin.framework.dao.DictTypeDao;
import my.admin.model.SysDictType;

@Service
public class DictTypeServiceImpl extends BaseServiceImpl<SysDictType, DictTypeDao> implements DictTypeService {
}

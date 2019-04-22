package my.admin.framework.service;

import org.springframework.stereotype.Service;

import my.admin.core.service.BaseServiceImpl;
import my.admin.framework.dao.ConfigDao;
import my.admin.model.SysConfig;

@Service
public class ConfigServiceImpl extends BaseServiceImpl<SysConfig, ConfigDao> implements ConfigService {
	
}
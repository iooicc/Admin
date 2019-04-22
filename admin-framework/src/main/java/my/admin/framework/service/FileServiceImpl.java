package my.admin.framework.service;

import org.springframework.stereotype.Service;

import my.admin.core.service.BaseServiceImpl;
import my.admin.framework.dao.FileDao;
import my.admin.model.SysFile;

@Service
public class FileServiceImpl extends BaseServiceImpl<SysFile, FileDao> implements FileService {
	
}
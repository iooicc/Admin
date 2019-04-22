package my.admin.framework.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.hutool.core.util.StrUtil;
import my.admin.core.service.BaseServiceImpl;
import my.admin.framework.dao.AdminDao;
import my.admin.model.SysAdmin;
import my.common.codec.EncodeUtils;
import my.common.idgen.IdGenerate;

@Service
public class AdminServiceImpl extends BaseServiceImpl<SysAdmin, AdminDao> implements AdminService {
	public boolean checkLoginCode(String oldLoginCode, String loginCode) {
		Map<String, Object> paras = new HashMap<String, Object>();
		
		if(!"".equals(oldLoginCode) && StrUtil.equals(oldLoginCode, loginCode)) {
			return true;
		}
		
		paras.put("loginCode", loginCode);
		SysAdmin row = executeSingle(
			"select"
			+" t1.user_code"
			+" from sys_user t1"
			+" where t1.login_code=#loginCode#"
			,paras
		);
		
		return loginCode != null && row == null ? true : false;
	}
	
	public void save(SysAdmin row) {
		String userCode = row.getUserCode(), password = row.getPassword();
		if(StrUtil.isNotEmpty(userCode)) {
			
			if(StrUtil.isNotEmpty(password)) {
				password = EncodeUtils.md5Hex(password);
				row.setPassword(password);
			} else {
				row.setPassword(null);
			}
			
			updateTemplateById(row);
		} else {
			row.setUserCode(IdGenerate.nextId());

			password = EncodeUtils.md5Hex(password);
			row.setPassword(password);
			
			
			insertTemplate(row);
		}
	}
}
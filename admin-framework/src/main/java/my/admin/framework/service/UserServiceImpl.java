package my.admin.framework.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import my.admin.core.service.BaseServiceImpl;
import my.admin.core.utils.BaseModelUtil;
import my.admin.framework.dao.UserDao;
import my.admin.model.SysOrg;
import my.admin.model.SysUser;
import my.admin.model.SysUserRole;
import my.common.codec.EncodeUtils;
import my.common.beans.Result;
import my.common.idgen.IdGenerate;
import my.common.shiro.IUser;

@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser, UserDao> implements UserService {
	
	@Override
	public IUser loadUserByUsername(String username) {
		SysUser row = query().andEq("login_code", username).single();
		return row;
	}
	
	public SysUser single(String userCode) {
		SysUser row = super.single(userCode);
		if(row==null) {
			return null;
		}
		
		String orgCode = row.getOrgCode();
		if(!StrUtil.isBlank(orgCode)) {
			SysOrg org = single(SysOrg.class, orgCode);
			row.setOrg(org);
		}
		
		return row;
	}
	
	public void page(PageQuery page) {
		Map<String, Object> paras = new HashMap<String, Object>();
		
		super.pageQuery("user.findList", SysUser.class, page);
		
		List<SysUser> list = page.getList();
		if(list==null||list.size()==0) {
			return;
		}
		
		String in = BaseModelUtil.getIn(list, "orgCode");
		
		List<SysOrg> orgs = execute(
			"select"
			+" t1.*"
			+" from sys_org t1"
			+" where t1.org_code in "+ in
			,SysOrg.class
			,paras
		);
		
		Map<String, SysOrg> mapOrgs = BaseModelUtil.getMap_Map(orgs, "orgCode");
		for(int i=0;i<list.size();i++) {
			SysUser ii = list.get(i);
			String orgCode = ii.getOrgCode();
			
			SysOrg org = mapOrgs.get(orgCode);
			ii.setOrg(org);

		}
	}
	
	public boolean checkLoginCode(String oldLoginCode, String loginCode) {
		Map<String, Object> paras = new HashMap<String, Object>();
		
		if(!"".equals(oldLoginCode) && StrUtil.equals(oldLoginCode, loginCode)) {
			return true;
		}
		
		paras.put("loginCode", loginCode);
		SysUser row = executeSingle(
			"select"
			+" t1.user_code"
			+" from sys_user t1"
			+" where t1.login_code=#loginCode#"
			,paras
		);
		
		return loginCode != null && row == null ? true : false;
	}
	
	public Result<SysUser> register_save(SysUser row) {
		Result<SysUser> result = new Result<SysUser>();
		String loginCode = row.getLoginCode(), password = row.getPassword();
		if(StrUtil.isBlank(loginCode)) {
			return result.setError("账户不能为空.");
		}
		
		if(StrUtil.isBlank(password)) {
			return result.setError("密码不能为空.");
		}
		
		boolean isValid = checkLoginCode("", loginCode);
		if(!isValid) {
			return result.setError("账户已经存在.");
		}
		
		String userCode = IdGenerate.nextId();
		String userName = loginCode;
		String corpCode = userCode;
		String salt = password;
		password = EncodeUtils.md5Hex(password);
		
		row.setUserCode(userCode);
		row.setUserName(userName);
		row.setCorpCode(corpCode);
		row.setPassword(password);
		row.setSalt(salt);
		insertTemplate(row);
		
		result.setData(row);
		return result;
	}
	
	public Result save(SysUser row) {
		Result result = new Result();
		String userCode = row.getUserCode(), loginCode = row.getLoginCode(), password = row.getPassword();
		if(!StrUtil.isBlank(userCode)) {
			
			if(!StrUtil.isBlank(password)) {
				String salt = password;
				password = EncodeUtils.md5Hex(password);
				row.setPassword(password);
				row.setSalt(salt);
			} else {
				row.setPassword(null);
			}
			
			SysOrg org = row.getOrg();
			if(org!=null) {
				String orgCode = org.getOrgCode();
				row.setOrgCode(orgCode);
			}
			
			saveAuth(row);
			updateTemplateById(row);
		} else {
			
			userCode = IdGenerate.nextId();
			String salt = password;
			password = EncodeUtils.md5Hex(password);
			
			SysOrg org = row.getOrg();
			if(org!=null) {
				String orgCode = org.getOrgCode();
				row.setOrgCode(orgCode);
			}
			
			row.setUserCode(userCode);
			row.setPassword(password);
			row.setSalt(salt);
			
			saveAuth(row);
			insertTemplate(row);
			
			result.setData(row);
		}
		return result;
	}
	
	@Transactional(readOnly = false)
	public void saveAuth(SysUser row) {
		Map<String, Object> paras = MapUtil.newHashMap();
		String userCode = row.getUserCode();
		if(!StrUtil.isBlank(row.getUserCode())) {
			paras.put("userCode", userCode);
			executeUpdate(
				"delete from sys_user_role where user_code=#userCode#"
				,paras
			);
			
			List<SysUserRole> userRoleList = row.getUserRoleList();
			if(userRoleList==null||userRoleList.size()==0) {
				return;
			}
			for(int i=0;i<userRoleList.size();i++) {
				SysUserRole ii = userRoleList.get(i);
				ii.setUserCode(userCode);
			}
			insertBatch(SysUserRole.class, userRoleList);
		}
	}
	
	public boolean validatePassword(String password, String userCode) {
		SysUser row = super.single(userCode);
		if(row==null) {
			return false;
		}
		
		password = EncodeUtils.md5Hex(password);
		return StrUtil.equals(row.getPassword(), password);
	}
	
	public void updatePassword(String password, String userCode) {
		String salt = password;
		password = EncodeUtils.md5Hex(password);
		
		SysUser row = new SysUser();
		row.setUserCode(userCode);
		row.setSalt(salt);
		row.setPassword(password);
		updateTemplateById(row);
	}
}
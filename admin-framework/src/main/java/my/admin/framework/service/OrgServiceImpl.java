package my.admin.framework.service;

import org.springframework.stereotype.Service;

import my.admin.core.service.BaseServiceImpl;
import my.admin.framework.dao.OrgDao;
import my.admin.model.SysOrg;

@Service
public class OrgServiceImpl extends BaseServiceImpl<SysOrg, OrgDao> implements OrgService {

	@Override
	public SysOrg single(String menuCode) {
		if("0".equals(menuCode)) {
			SysOrg root = new SysOrg();
			root.setOrgCode("0");
			root.setTreeLevel(0);
			return root;
		}
		
		SysOrg row = super.single(menuCode);
		String parentCode = null;
		if(row==null || (parentCode=row.getParentCode())==null) {
			return null;
		}
		
		SysOrg parent = super.single(parentCode);
		row.setParent(parent);
		
		return row;
	}

}
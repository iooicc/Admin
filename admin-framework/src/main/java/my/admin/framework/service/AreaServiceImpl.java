package my.admin.framework.service;

import org.springframework.stereotype.Service;
import my.admin.core.service.BaseServiceImpl;
import my.admin.framework.dao.AreaDao;
import my.admin.model.Area;

@Service
public class AreaServiceImpl extends BaseServiceImpl<Area, AreaDao> implements AreaService {
	public Area single(String menuCode) {
		
		if("0".equals(menuCode)) {
			Area root = new Area();
			root.setAreaCode("0");
			root.setTreeLevel(0);
			return root;
		}
		
		Area row = super.single(menuCode);
		String parentCode = null;
		if(row==null || (parentCode=row.getParentCode())==null) {
			return null;
		}		
		Area parent = super.single(parentCode);
		row.setParent(parent);
		
		return row;
	}
	
}
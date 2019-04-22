package my.admin.framework.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import my.admin.core.service.BaseServiceImpl;
import my.admin.framework.dao.MsgInnerDao;
import my.admin.model.MsgInner;

@Service
public class MsgInnerServiceImpl extends BaseServiceImpl<MsgInner, MsgInnerDao> implements MsgInnerService {
	
	@Autowired
	SQLManager sqlManager;
	
	public void page(PageQuery page) {
		
		Map<String, Object> paras = new HashMap<String, Object>();
		
		pageQuery("msgInner.findList", MsgInner.class, page);
		
		List<MsgInner> list = page.getList();
		if(list!=null && list.size()>0) {
			
			
		
			
		}
	}
}
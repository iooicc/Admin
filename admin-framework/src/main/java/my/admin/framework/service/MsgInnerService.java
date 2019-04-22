package my.admin.framework.service;

import org.beetl.sql.core.engine.PageQuery;

import my.admin.core.service.BaseService;
import my.admin.model.MsgInner;

public interface MsgInnerService extends BaseService<MsgInner> {
	void page(PageQuery query);
}

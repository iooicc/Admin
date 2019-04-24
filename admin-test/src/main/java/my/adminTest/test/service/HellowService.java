package my.adminTest.test.service;

import my.admin.core.service.BaseService;
import my.adminTest.test.model.Hellow;
import org.beetl.sql.core.engine.PageQuery;

public interface HellowService extends BaseService<Hellow>{

    void page(PageQuery query);

}

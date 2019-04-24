package my.adminTest.test.service;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.stereotype.Service;
import my.admin.core.service.BaseServiceImpl;
import my.adminTest.test.dao.HellowDao;
import my.adminTest.test.model.Hellow;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HellowServiceImpl  extends BaseServiceImpl<Hellow,HellowDao>  implements HellowService{

    @Override
    public void page(PageQuery query) {
        pageQuery("hellow.findList", Hellow.class, query);
        List<Hellow> list = query.getList();

    }
}

package my.adminTest.test.service;

import org.springframework.stereotype.Service;

import my.admin.core.service.BaseServiceImpl;
import my.adminTest.test.dao.HellowDao;
import my.adminTest.test.model.Hellow;

@Service
public class HellowServiceImpl  extends BaseServiceImpl<Hellow,HellowDao>  implements HellowService{

}

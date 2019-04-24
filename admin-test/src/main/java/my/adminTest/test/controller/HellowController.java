package my.adminTest.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import my.admin.dto.Page;
import my.adminTest.test.model.Hellow;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import my.admin.core.controller.BaseController;
import my.common.beans.Result;
import my.adminTest.test.service.HellowService;

@Controller
@RequestMapping({ "${project.context-path}/hellow" })
public class HellowController extends BaseController{

	@Autowired
	HellowService hellowService;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	@ResponseBody
	public Result index(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String,Object> paras=new HashMap<>();
		paras.put("username", "admin");
		
		List<Map> list = hellowService.execute(
				"select t1.user_code AS 'id'"
				+ ",t1.*"
				+ "from sys_user t1 "
				+ " WHERE t1.login_code=#username#"
				,Map.class
				, paras);
		
		return Result.success("操作成功！", list);
		
	}

	@RequestMapping(value = "index1", method = RequestMethod.GET)
	@ResponseBody
	public Page index1() {

		Hellow row =new Hellow();
		row.setUserName("管理员");
		PageQuery<Hellow> query = new PageQuery<Hellow>(1, 20, row);
		hellowService.page(query);
		return new Page(query);

	}
		
	
}

package my.adminTest.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		paras.put("username", "Wgaoshang");
		
		List<Map> list = hellowService.execute(
				"select t1.uid"
				+ ",t1.data"
				+ ",JSON_EXTRACT(t1.data,'$.features[0].age') as age"
				+ ",JSON_EXTRACT(t1.data,'$.features[1].height') as height"
				+ ",user_name "
				+ "from user t1 " 
				+ " WHERE t1.user_name=#username#"
				,Map.class
				, paras);
		
		return Result.success("操作成功！", list);
		
	}
		
		
	
}

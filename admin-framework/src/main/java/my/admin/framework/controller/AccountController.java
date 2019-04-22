package my.admin.framework.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import my.admin.framework.event.producer.AccountEventProducer;
import my.admin.framework.service.UserService;
import my.admin.model.SysUser;
import my.common.beans.Result;


@Controller
@RequestMapping(value = "/account")
public class AccountController {
	
	@Autowired
	UserService userService;
	@Autowired
	AccountEventProducer accountEventProducer;
	
	/**
	 * 用户注册页面
	 * @param user 用户信息参数
	 */
	@RequestMapping(value = "register")
	public String registerUser(SysUser user, HttpServletRequest request) {
		return "modules/framework/account__register";
	}
	
	@PostMapping(value = "register_save")
	@ResponseBody
	public Result register_save(SysUser row, String op) {
		Result<SysUser> result = userService.register_save(row);
		
		String userCode = null;
		if(result!=null
			&& (row=result.getData())!=null
			&& (userCode=row.getUserCode())!=null
		) {
			accountEventProducer.onNew(userCode);
		}
		return Result.SUCCESS;
	}
	
	
}

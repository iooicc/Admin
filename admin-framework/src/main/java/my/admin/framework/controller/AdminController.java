package my.admin.framework.controller;

import java.util.HashMap;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import my.admin.dto.Page;
import my.admin.framework.service.AdminService;
import my.admin.model.SysAdmin;
import my.common.beans.Result;
import my.common.idgen.IdGenerate;

@Controller
@RequestMapping({ "/admin" })
public class AdminController {
	
	@Autowired
	AdminService modelService;
	
	@RequestMapping({ "list" })
	public String list(SysAdmin condition, Model model) {
		model.addAttribute("condition", condition);
		return "modules/framework/admin__list";
	}
	
	@RequestMapping({ "listData" })
	@ResponseBody
	public Page listData(
		SysAdmin condition,
		String roleCode,
		@RequestParam(defaultValue="1") Long pageNo
	) {
		condition.set("roleCode", roleCode);
		PageQuery<SysAdmin> query = new PageQuery<SysAdmin>(pageNo, 20, condition);
		modelService.pageQuery("admin.findList", SysAdmin.class, query);
		
		return new Page(query);
	}
	
	@RequestMapping(value = "form")
	public String form(SysAdmin row, String op, Model model) {
		String key = row.getUserCode();
		if(StrUtil.isNotEmpty(key)) {
			row = modelService.single(key);
			row.setPassword("");
		}
		
		model.addAttribute("op", op);
		model.addAttribute("row", row);
		return "modules/framework/admin__form";
	}
	
	@PostMapping(value = "save")
	@ResponseBody
	public Result save(SysAdmin row, String op) {
		modelService.save(row);
		return Result.SUCCESS;
	}
	
	@RequestMapping({"checkLoginCode"})
	@ResponseBody
	public String checkLoginCode(String oldLoginCode, String loginCode) {
		return modelService.checkLoginCode(oldLoginCode, loginCode)?"true":"false";
	}
	
	@RequestMapping({"userSelect"})
	public String userSelect(SysAdmin row, String selectData, String checkbox, Model model) {
		
		
		model.addAttribute("selectData", URLUtil.decode(selectData));
		model.addAttribute("user", row);
		model.addAttribute("checkbox", checkbox);
		return "modules/framework/admin__userSelect";
	}
}

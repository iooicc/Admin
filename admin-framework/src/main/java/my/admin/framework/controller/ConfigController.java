package my.admin.framework.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import my.admin.dto.Page;
import my.admin.framework.service.ConfigService;
import my.admin.framework.util.ConfigUtil;
import my.admin.model.SysConfig;
import my.admin.model.SysRole;
import my.common.beans.Result;
import my.common.idgen.IdGenerate;

@Controller
@RequestMapping({"/config"})
public class ConfigController {
	
	@Autowired
	ConfigService modelService;
	
	@RequestMapping({"list"})
	public String list(SysConfig row, Model model) {
		model.addAttribute("row", row);
		return "modules/framework/config__list";
	}
	
	@RequestMapping({"listData"})
	@ResponseBody
	public Page listData(
		SysConfig row
		,@RequestParam(defaultValue="1") Long pageNo
		,HttpServletRequest request
		,HttpServletResponse response
	) {
		PageQuery<SysConfig> condition = new PageQuery<SysConfig>(pageNo, 20, row);
		modelService.pageQuery("config.findList", SysConfig.class, condition);
		
		return new Page(condition);
	}
	
	@RequestMapping({"form"})
	public String form(
		SysConfig row
		, String op
		, Model model
	) {
		String id = row.getId();
		if(!StrUtil.isBlank(id)) {
			row = modelService.single(id);
		}
		
		model.addAttribute("op", op);
		model.addAttribute("row", row);
		return "modules/framework/config__form";
	}
	
	@RequestMapping({"checkConfigKey"})
	@ResponseBody
	public String checkConfigKey(String oldConfigKey, String configKey) {
		
		Map<String, Object> paras = MapUtil.newHashMap();
		if(configKey!=null && configKey.equals(oldConfigKey)) {
			return "true";
		}
		
		paras.put("configKey", configKey);
		long cnt = modelService.count(
			"select"
			+" count(*)"
			+" from sys_config t1"
			+" where t1.config_key=#configKey#"
			,paras
		);
		
		return configKey!=null && cnt==0L?"true":"false";
	}
	
	@PostMapping({"save"})
	@ResponseBody
	public Result save(@Validated SysConfig row, HttpServletRequest request) {
		
		String id = row.getId();
		if(StrUtil.isBlank(id)) {
			id = IdGenerate.nextId();
			row.setId(id);
			
			modelService.insertTemplate(row);
		} else {
			
			modelService.updateTemplateById(row);
		}
		ConfigUtil.clearCache();
		return Result.SUCCESS;
	}
	
	@RequestMapping({"delete"})
	@ResponseBody
	public Result delete(SysConfig row, HttpServletRequest request) {
		Result result = new Result();
		String id = null;
		if(row!=null
			&& (id=row.getId())!=null
			&& !StrUtil.isBlank(id)
			&& (row=modelService.single(id))!=null
		) {
			
			String isSys = row.getIsSys();
			if("1".equals(isSys)) {
				return result.setError("系统参数无法删除.");
			}
			
			modelService.deleteById(id);
		}
		
		ConfigUtil.clearCache();
		return Result.SUCCESS;
	}
	
	@RequestMapping({"clearAll"})
	@ResponseBody
	public Result clearAll(SysConfig row, HttpServletRequest request) {
		ConfigUtil.clearCache();
		return Result.SUCCESS;
	}
}

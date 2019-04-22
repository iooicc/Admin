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
import my.admin.framework.service.DictTypeService;
import my.admin.framework.util.DictUtil;
import my.admin.model.SysDictType;
import my.common.beans.Result;
import my.common.idgen.IdGenerate;

@Controller
@RequestMapping({"/dictType"})
public class DictTypeController {
	@Autowired
	private DictTypeService modelService;
	
	@RequestMapping({"list"})
	public String list(SysDictType row, Model model) {
		model.addAttribute("row", row);
		return "modules/framework/dictType__list";
	}
	
	@RequestMapping({"listData"})
	@ResponseBody
	public Page listData(
		SysDictType row
		,@RequestParam(defaultValue="1") Long pageNo
		,HttpServletRequest request
		,HttpServletResponse response
	) {
		PageQuery<SysDictType> query = new PageQuery<SysDictType>(pageNo, 20, row);
		modelService.pageQuery("dictType.page", SysDictType.class, query);
		
		return new Page(query);
	}
	
	@RequestMapping({"form"})
	public String form(SysDictType row, String op, Model model) {
		String id = row.getId();
		if(StrUtil.isBlank(id)) {
			
		} else {
			row = modelService.single(id);
		}

		model.addAttribute("row", row);
		model.addAttribute("op", op);
		return "modules/framework/dictType__form";
	}
	
	@RequestMapping({"checkDictType"})
	@ResponseBody
	public String checkDictType(String oldDictType, String dictType) {
		Map<String, Object> paras = MapUtil.newHashMap();
		
		if(dictType!=null && dictType.equals(oldDictType)) {
			return "true";
		}
		
		paras.put("dictType", dictType);
		long cnt = modelService.count(
			"select"
			+" count(*)"
			+" from sys_dict_type t1"
			+" where t1.dict_type=#dictType#"
			,paras
		);
		
		return dictType != null && cnt == 0L ? "true" : "false";
	}
	
	@PostMapping({"save"})
	@ResponseBody
	public Result save(@Validated SysDictType row, HttpServletRequest request) {
		
		String id = row.getId();
		if(StrUtil.isBlank(id)) {
			id = IdGenerate.nextId();
			row.setId(id);
			
			modelService.insertTemplate(row);
		} else {
			modelService.updateTemplateById(row);
		}
		
		DictUtil.clearCache();
		
		return Result.SUCCESS;
	}
	
	@RequestMapping({"delete"})
	@ResponseBody
	public Result delete(SysDictType row, HttpServletRequest request) {
		
		Map<String, Object> paras = MapUtil.newHashMap();
		String id = row.getId();
		if(!StrUtil.isBlank(id)
			&& (row = modelService.single(id))!=null
		) {
			modelService.deleteById(id);
			
			String dictType = row.getDictType();
			paras.put("dictType", dictType);
			modelService.executeUpdate(
				"delete from sys_dict_data where dict_type=#dictType#"
				,paras
			);
		}
		return Result.SUCCESS;
	}
}
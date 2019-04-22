package my.admin.framework.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.core.util.StrUtil;
import my.admin.framework.service.DictDataService;
import my.admin.framework.util.DictUtil;
import my.admin.model.SysDictData;
import my.common.beans.Result;
import my.common.idgen.IdGenerate;

@Controller
@RequestMapping({"/dictData"})
public class DictDataController {
	
	@Autowired
	DictDataService modelService;
	
	@RequestMapping({"list"})
	public String list(SysDictData row, Model model) {
		model.addAttribute("row", row);
		return "modules/framework/dictData__list";
	}
	
	@RequestMapping({"listData"})
	@ResponseBody
	public List<SysDictData> listData(SysDictData row) {
		if (StrUtil.isBlank(row.getParentCode())) {
			row.setParentCode("0");
		}
		
		return modelService.select("dictData.findList", SysDictData.class, row);
	}
	
	@RequestMapping({"form"})
	public String form(SysDictData row, String op, Model model) {
		String dictCode = row.getDictCode();
		if(!StrUtil.isBlank(dictCode)) {
			row = modelService.single(dictCode);
		}
		
		row = this.createNextNode(row);
		model.addAttribute("op", op);
		model.addAttribute("row", row);
		return "modules/framework/dictData__form";
	}
	
	public SysDictData createNextNode(SysDictData row) {
		String parentCode = row.getParentCode();
		if(!StrUtil.isBlank(parentCode)) {
			row.setParent(modelService.single(parentCode));
		}
		
		return row;
	}
	
	@PostMapping({"save"})
	@ResponseBody
	public Result save(SysDictData row, HttpServletRequest request) {
		// TODO
		// 暂不支持层级结构
		String dictCode = row.getDictCode();
		if(StrUtil.isBlank(dictCode)) {
			dictCode = IdGenerate.nextId();
			row.setDictCode(dictCode);
			row.setParentCode("0");
			row.setParentCodes("0,");
			
			Long treeSort = row.getTreeSort();
			row.setTreeSorts(String.format("%s,", treeSort));
			
			row.setTreeLeaf("1");
			row.setTreeLevel(0);
			
			String dictLabel = row.getDictLabel();
			row.setTreeNames(String.format("%s,", dictLabel));
			
			modelService.insertTemplate(row);
		} else {
			modelService.updateTemplateById(row);
		}
		
		DictUtil.clearCache();
		
		return Result.SUCCESS;
	}
}

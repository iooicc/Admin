package my.admin.framework.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import my.admin.core.controller.BaseController;
import my.admin.framework.service.AreaService;
import my.admin.model.Area;
import my.common.beans.Result;
import my.common.idgen.IdGenerate;

@Controller
@RequestMapping({ "/area" })
public class AreaController extends BaseController {
	
	@Autowired
	AreaService modelService;

	@RequestMapping({ "list" })
	public String list(Area row, Model model) {
		model.addAttribute("row", row);
		return "modules/framework/area__list";
	}

	@RequestMapping({ "listData" })
	@ResponseBody
	public List listData(
		String areaName,
		String status,
		String areaCode,
		String parentCode
	) {
		if(StrUtil.isBlank(areaName)) {
		}
		if(StrUtil.isBlank(parentCode)) {
			parentCode = "0";
		}
		
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("areaCode", areaCode);
		paras.put("areaName", areaName);
		paras.put("status", status);
		paras.put("parentCode", parentCode);
		List list = modelService.select("area.findList", Area.class, paras);
		
		return list;
	}

	@RequestMapping({ "form" })
	public String form(
		String areaCode,
		String parentCode,
		Model model) {
		
		Area area = null, parent = null;
		if(StrUtil.isNotEmpty(areaCode)) {
			area = modelService.single(areaCode);
		}
		if(area!=null) {
			parentCode = area.getParentCode();
			parent = modelService.single(parentCode);
		} else {
			area = new Area();
			if(StrUtil.isNotEmpty(parentCode)) {
				parent = modelService.single(parentCode);
			}
		}
		if(parent==null) {
			parent = new Area();
			parent.setAreaCode("0");
			parent.setAreaName("");
		}
		area.setParent(parent);
		
		model.addAttribute("area", area);
		return "modules/framework/area__form";
	}
	
	@RequestMapping({"treeData"})
	@ResponseBody
	public List treeData() {
		Map<String, Object> paras = new HashMap<String, Object>();
		List list = modelService.execute(
			"select"
			+" t1.area_code as id"
			+",t1.parent_code as `pId`"
			+",t1.area_name as name"
			+" from sys_area t1"
			,HashMap.class
			,paras
		);
		return list;
	}
	
	@RequestMapping({"treeData2"})
	@ResponseBody
	public String treeData2(String state, String selectId, String callback) {
		Map<String, Object> paras = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(StrUtil.isBlank(selectId)
			|| "1".equals(selectId)) {
			selectId = "0";
		}
		
		paras.put("pid", selectId);
		List list = modelService.execute(
			"select"
			+" t1.area_code as `id`"
			+",t1.area_code as `code`"
			+",t1.parent_code as `pid`"
			+",t1.area_name as `name`"
			+",false as `ismunicipality`"
			+" from sys_area t1"
			+" where t1.parent_code=#pid#"
			,HashMap.class
			,paras
		);
		
		result.put("data", list);
		
		if(!StrUtil.isBlank(callback)) {
			return String.format("%s(%s)", callback, JSONUtil.toJsonStr(result));
		}
		return JSONUtil.toJsonStr(result);
	}
	
	/**
	 * 省市两级数据
	 * @param callback
	 * @return
	 */
	@RequestMapping({"treeData3"})
	@ResponseBody
	public String treeData3(String callback) {
		Map<String, Object> paras = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		
		List list = modelService.execute(
			"select"
			+" t1.area_code as `id`"
			+",t1.area_code as `code`"
			+",t1.parent_code as `pid`"
			+",t1.area_name as `name`"
			+",false as `ismunicipality`"
			+" from sys_area t1"
			+" where t1.tree_level = 0"
			+" or t1.tree_level = 1"
			,HashMap.class
			,paras
		);
		
		result.put("data", list);
		
		if(!StrUtil.isBlank(callback)) {
			return String.format("%s(%s)", callback, JSONUtil.toJsonStr(result));
		}
		return JSONUtil.toJsonStr(result);
	}

	@PostMapping({ "save" })
	@ResponseBody
	public Result save(Area row) {
		
		Result result = new Result();
		
		String areaCode = row.getAreaCode();
		if(!StrUtil.isBlank(areaCode)) {
			
			Area parent = row.getParent();
			String parentCode = null;
			if(parent!=null && (parentCode=parent.getParentCode())!=null) {
				row.setParentCode(parentCode);
			}
			
			modelService.updateTemplateById(row);
		} else {
			
			Area parent = row.getParent();
			String parentCode = null;
			if(parent==null || StrUtil.isBlank(parentCode=parent.getAreaCode())) {
				return result.setError("参数错误[1].");
			}
			
			parent = modelService.single(parentCode);
			if(parent==null) {
				return result.setError("参数错误[2].");
			}
			
			areaCode = IdGenerate.nextId();
			Integer treeLevel = parent.getTreeLevel();
			treeLevel++;
			
			row.setAreaCode(areaCode);
			row.setParentCode(parentCode);
			row.setTreeLevel(treeLevel);

			modelService.insertTemplate(row);
		}
		return Result.SUCCESS;
	}
	
	@PostMapping({ "delete" })
	@ResponseBody
	public Result delete(String areaCode) {
		if(StrUtil.isBlank(areaCode)) {
			return Result.error("参数为空");
		}
		Area row = modelService.single(Area.class, areaCode);
		if(null == row) {
			return Result.error("参数对应数据未查到");
		}
		Map<String, Object> paras=new ConcurrentHashMap<>();
		paras.put("parentCode", areaCode);
		List<Area> list = modelService.execute(
				"select"
				+" t1.* "
				+" from sys_area t1"
				+" where t1.status <> '1' and t1.parent_code =#parentCode#",
				Area.class, 
				paras
		);		
		if(list.size()>0) {
			return Result.error("该区域包含未删除的父区域！");
		}
		
		if(!"1".equals(row.getStatus().toString())) {
			Area obj = new Area();
			obj.setAreaCode(areaCode);
			obj.setStatus("1");
			modelService.updateTemplateById(obj);
			return Result.SUCCESS;
		}
		return Result.error("重复操作");
	}
	
	
	@PostMapping({ "disable" })
	@ResponseBody
	public Result disable(String areaCode) {
		if(StrUtil.isBlank(areaCode)) {
			return Result.error("参数为空");
		}
		Area row = modelService.single(Area.class, areaCode);
		if(null == row) {
			return Result.error("参数对应数据未查到");
		}
		Map<String, Object> paras=new ConcurrentHashMap<>();
		paras.put("parentCode", areaCode);
		List<Area> list = modelService.execute(
				"select"
				+" t1.* "
				+" from sys_area t1"
				+" where t1.status = '0' and t1.parent_code =#parentCode#",
				Area.class, 
				paras
		);		
		if(list.size()>0) {
			return Result.error("该区域包含未停用的子区域！");
		}
		
		if("0".equals(row.getStatus().toString())) {
			Area obj = new Area();
			obj.setAreaCode(areaCode);
			obj.setStatus("2");
			modelService.updateTemplateById(obj);
			return Result.SUCCESS;
		}		
		return Result.error("重复操作");
		
	}
	
	@PostMapping({ "enable" })
	@ResponseBody
	public Result enable(String areaCode) {
		if(StrUtil.isBlank(areaCode)) {
			return Result.error("参数为空");
		}
		Area row = modelService.single(Area.class, areaCode);
		if(null == row) {
			return Result.error("参数对应数据未查到");
		}
		Map<String, Object> paras=new ConcurrentHashMap<>();
		paras.put("parentCode", areaCode);
		List<Area> list = modelService.execute(
				"select"
				+" t1.* "
				+" from sys_area t1"
				+" where t1.status = '2' and t1.parent_code =#parentCode#",
				Area.class, 
				paras
		);		
		if(list.size()>0) {
			return Result.error("该区域包含未启用的父区域！");
		}
		
		if("2".equals(row.getStatus().toString())) {
			Area obj = new Area();
			obj.setAreaCode(areaCode);
			obj.setStatus("0");
			modelService.updateTemplateById(obj);
			return Result.SUCCESS;			
		}
		return Result.error("重复操作");
	}
	
	
}

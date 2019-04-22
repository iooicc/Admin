package my.admin.framework.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.beetl.sql.core.TailBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.hutool.core.util.StrUtil;
import my.admin.core.controller.BaseController;
import my.admin.dto.SortsDto;
import my.admin.framework.service.MenuService;
import my.admin.model.SysMenu;
import my.common.beans.Result;
import my.common.config.Global;
import my.common.idgen.IdGenerate;

@Controller
@RequestMapping({ "/menu" })
public class MenuController extends BaseController {
	
	@Autowired
	MenuService modelService;

	@RequestMapping({ "list" })
	public String list(SysMenu row, Model model) {
		model.addAttribute("row", row);
		return "modules/framework/menu__list";
	}

	@RequestMapping({ "listData" })
	@ResponseBody
	public List listData(
		String menuName,
		String parentCode
	) {
		if(StrUtil.isBlank(menuName)) {
		}
		if(StrUtil.isBlank(parentCode)) {
			parentCode = "0";
		}
		
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("menuName", menuName);
		paras.put("parentCode", parentCode);
		List list = modelService.select("menu.findList", SysMenu.class, paras);
		
		return list;
	}

	@RequestMapping({ "form" })
	public String form(
		String menuCode,
		String parentCode,
		Model model) {
		
		SysMenu menu = null, parent = null;
		if(StrUtil.isNotEmpty(menuCode)) {
			menu = modelService.single(menuCode);
		}
		if(menu!=null) {
			parentCode = menu.getParentCode();
			parent = modelService.single(parentCode);
		} else {
			menu = new SysMenu();
			String sysCode=Global.getProperty("project.context-path","/default").replace("/", "");
			menu.setSysCode(sysCode);
			
			if(StrUtil.isNotEmpty(parentCode)) {
				parent = modelService.single(parentCode);
				//防止取默认的
				if(null !=parent) {
					menu.setSysCode(parent.getSysCode());
				}
			}
		}
		if(parent==null) {
			parent = new SysMenu();
			parent.setMenuCode("0");
			parent.setMenuName("");
		}
		menu.setParent(parent);
		
		model.addAttribute("menu", menu);
		return "modules/framework/menu__form";
	}
	
	@RequestMapping({"treeData"})
	@ResponseBody
	public List treeData() {
		Map<String, Object> paras = new HashMap<String, Object>();
		List list = modelService.execute(
			"select"
			+" t1.menu_code as id"
			+",t1.parent_code as `pId`"
			+",t1.menu_name as name"
			+" from sys_menu t1"
			,HashMap.class
			,paras
		);
		return list;
	}

	@PostMapping({ "save" })
	@ResponseBody
	public Result save(SysMenu row) {
		
		Result result = new Result();
		
		String menuCode = row.getMenuCode();
		if(!StrUtil.isBlank(menuCode)) {
			
			SysMenu parent = row.getParent();
			String parentCode = null;
			if(parent!=null && (parentCode=parent.getParentCode())!=null) {
				row.setParentCode(parentCode);
			}
			
			modelService.updateTemplateById(row);
		} else {
			
			SysMenu parent = row.getParent();
			String parentCode = null;
			if(parent==null || StrUtil.isBlank(parentCode=parent.getMenuCode())) {
				return result.setError("参数错误[1].");
			}
			
			parent = modelService.single(parentCode);
			if(parent==null) {
				return result.setError("参数错误[2].");
			}
			
			menuCode = IdGenerate.nextId();
			Integer treeLevel = parent.getTreeLevel();
			treeLevel++;
			
			row.setMenuCode(menuCode);
			row.setParentCode(parentCode);
			row.setTreeLevel(treeLevel);

			modelService.insertTemplate(row);
		}
		return Result.SUCCESS;
	}
	
	@PostMapping({ "delete" })
	@ResponseBody
	public Result delete(SysMenu menu) {
		if(menu!=null) {
			String pk = menu.getMenuCode();
			modelService.deleteById(pk);
		}
		return Result.SUCCESS;
	}
	
	@PostMapping({ "updateTreeSort" })
	@ResponseBody
	public Result updateTreeSort(
		SortsDto dto
	) {
		String ID_FIELD_NAME = "menuCode";//
		Map<String, String> sorts = dto.getSorts();
		Iterator<String> ite = sorts.keySet().iterator();
		List<TailBean> list = new ArrayList<TailBean>();
		while(ite.hasNext()) {
			String key = ite.next();
			String value = sorts.get(key);
			
			TailBean bean = new TailBean();
			bean.set(ID_FIELD_NAME, key);
			bean.set("treeSort", value);
			list.add(bean);
		}
		modelService.updateBatchTemplateById(SysMenu.class, list);
		return Result.SUCCESS;
	}
}

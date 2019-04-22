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
import my.admin.framework.service.OrgService;
import my.admin.framework.util.UserUtil;
import my.admin.model.SysOrg;
import my.admin.model.SysUser;
import my.common.beans.Result;
import my.common.idgen.IdGenerate;

@Controller
@RequestMapping({ "/org" })
public class OrgController extends BaseController {
	
	@Autowired
	OrgService modelService;

	@RequestMapping({ "list" })
	public String list(Model model) {
		return "modules/framework/org__list";
	}

	@RequestMapping({ "listData" })
	@ResponseBody
	public List listData(
		String orgName,
		String parentCode
	) {
		SysUser user = UserUtil.getUser();
		
		if(StrUtil.isBlank(orgName)) {
		}
		if(StrUtil.isBlank(parentCode)) {
			parentCode = "0";
		}
		
		String corpCode = user.getCorpCode();
		
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("orgName", orgName);
		paras.put("parentCode", parentCode);
		paras.put("corpCode", corpCode);
		List list = modelService.select("org.findList", SysOrg.class, paras);
		
		return list;
	}

	@RequestMapping({ "form" })
	public String form(
		String orgCode,
		String parentCode,
		Model model) {
		
		SysOrg org = null, parent = null;
		if(StrUtil.isNotEmpty(orgCode)) {
			org = modelService.single(orgCode);
		}
		if(org!=null) {
			parentCode = org.getParentCode();
			parent = modelService.single(parentCode);
		} else {
			org = new SysOrg();
			if(StrUtil.isNotEmpty(parentCode)) {
				parent = modelService.single(parentCode);
			}
		}
		if(parent==null) {
			parent = new SysOrg();
			parent.setOrgCode("0");
			parent.setOrgName("");
		}
		org.setParent(parent);
		
		model.addAttribute("org", org);
		return "modules/framework/org__form";
	}
	
	@RequestMapping({"treeData"})
	@ResponseBody
	public List treeData() {
		Map<String, Object> paras = new HashMap<String, Object>();
		
		SysUser user = UserUtil.getUser();
		
		String corpCode = user.getCorpCode();
		
		paras.put("corpCode", corpCode);
		List list = modelService.execute(
			"select"
			+" t1.org_code as id"
			+",t1.parent_code as `pId`"
			+",t1.org_name as name"
			+",t1.full_name as title"
			+" from sys_org t1"
			+" where t1.corp_code=#corpCode#"
			,HashMap.class
			,paras
		);
		return list;
	}

	@PostMapping({ "save" })
	@ResponseBody
	public Result save(SysOrg org) {
		
        Result result = new Result();
        String orgCode = org.getOrgCode();
		if(!StrUtil.isBlank(orgCode)) {
			
			SysOrg parent = org.getParent();
			String parentCode = null;
			if(parent!=null && (parentCode=parent.getParentCode())!=null) {
				org.setParentCode(parentCode);
			}
			
			modelService.updateTemplateById(org);
		} else {
			
			SysOrg parent = org.getParent();
			String parentCode = null;
			if(parent==null || StrUtil.isBlank(parentCode=parent.getOrgCode())) {
				return result.setError("参数错误[1].");
			}
			
			parent = modelService.single(parentCode);
			if(parent==null) {
				return result.setError("参数错误[2].");
			}
			
			orgCode = IdGenerate.nextId();
			Integer treeLevel = parent.getTreeLevel();
			treeLevel++;
			
			org.setOrgCode(orgCode);
			org.setParentCode(parentCode);
			org.setTreeLevel(treeLevel);

			modelService.insertTemplate(org);
		}
		return Result.SUCCESS;
	}
	
	@PostMapping({ "delete" })
	@ResponseBody
	public Result delete(SysOrg org) {
		if(org!=null) {
			String pk = org.getOrgCode();
			modelService.deleteById(pk);
		}
		return Result.SUCCESS;
	}
	
	@PostMapping({ "updateTreeSort" })
	@ResponseBody
	public Result updateTreeSort(
		SortsDto dto
	) {
		String ID_FIELD_NAME = "orgCode";//
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
		modelService.updateBatchTemplateById(SysOrg.class, list);
		return Result.SUCCESS;
	}
}

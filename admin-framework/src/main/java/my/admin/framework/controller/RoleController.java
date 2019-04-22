package my.admin.framework.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.hutool.core.util.StrUtil;
import my.admin.core.controller.BaseController;
import my.admin.core.utils.BaseModelUtil;
import my.admin.dto.Page;
import my.admin.framework.service.MenuService;
import my.admin.framework.service.RoleService;
import my.admin.framework.service.UserService;
import my.admin.model.SysMenu;
import my.admin.model.SysRole;
import my.admin.model.SysRoleDataScope;
import my.admin.model.SysUser;
import my.common.beans.Result;

@Controller
@RequestMapping({ "/role" })
public class RoleController extends BaseController {
	
	@Autowired
	RoleService modelService;
	@Autowired
	MenuService menuService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping({ "list" })
	public String list(
		SysRole row,
		Model model
	) {
		model.addAttribute("row", row);
		return "modules/framework/role__list";
	}
	
	@RequestMapping({ "listData" })
	@ResponseBody
	public Page listData(
		SysRole role,
		@RequestParam(defaultValue="1") Long pageNo
	) {
		
		PageQuery<SysRole> condition = new PageQuery<SysRole>(pageNo, 20, role);
		modelService.pageQuery("role.findList", SysRole.class, condition);
		
		return new Page(condition);
	}
	
	@RequestMapping({ "form" })
	public String form(
		String roleCode,
		String op,
		Model model) {
		
		SysRole role = null;
		if(StrUtil.isNotEmpty(roleCode)) {
			role = modelService.single(roleCode);
			
		}
		
		if(role==null) {
			role = new SysRole();
		} else {
		}
		
		model.addAttribute("role", role);
		model.addAttribute("op", op);
		return "modules/framework/role__form";
	}
	
	@RequestMapping({ "menuTreeData" })
	@ResponseBody
	public Map<String, Object> menuTreeData(
		SysRole role
	) {
		Map<String, Object> paras = new HashMap<String, Object>(), result = new HashMap<String, Object>();
		
		SysMenu menu = new SysMenu();
		List<SysMenu> list = menuService.template(menu);
		
		Map<String, List<SysMenu>> mapList = BaseModelUtil.getMap_List(list, "sysCode");
		result.put("menuMap", mapList);
		
		List<Map> roleMenuList = Collections.EMPTY_LIST;
		if(role!=null && StrUtil.isNotEmpty(role.getRoleCode())) {
			paras.put("roleCode", role.getRoleCode());
			roleMenuList = modelService.execute(
				"select"
				+" t1.*"
				+",t2.sys_code"
				+" from sys_role_menu t1, sys_menu t2"
				+" where t1.menu_code=t2.menu_code"
				+" and t1.role_code=#roleCode#"
				,Map.class
				,paras
			);
		}
		result.put("roleMenuList", roleMenuList);
		
		return result;
	}
	
	@PostMapping({"save"})
	@ResponseBody
	public Result save(SysRole row, String op, HttpServletRequest request) {
		Map<String, Object> paras = new HashMap<String, Object>();
		
		String roleCode = row.getRoleCode();
		
		if("add".equals(op)) {
			paras.put("roleCode", roleCode);
			SysRole condition = modelService.executeSingle(
				"select"
				+" t1.role_code"
				+" from sys_role t1"
				+" where t1.role_code=#roleCode#"
				,paras
			);
			if(condition!=null) {
				return new Result().setError("角色编码已经存在");
			}
			modelService.insertTemplate(row);
			modelService.saveAuth(row);
		} else if("edit".equals(op)) {
			modelService.updateTemplateById(row);
			modelService.saveAuth(row);
		}
		
		return Result.SUCCESS;
	}
	
	@RequestMapping({"saveAuthUser"})
	@ResponseBody
	public Result saveAuthUser(SysRole row, HttpServletRequest request) {
		modelService.saveAuthUser(row);
		return Result.SUCCESS;
	}
	
	@RequestMapping({"formAuthUser"})
	public String formAuthUser(SysRole role, Model model, HttpServletRequest request) {
		
		String roleCode = role.getRoleCode();
		role = modelService.single(roleCode);
		
		model.addAttribute("role", role);
		return "modules/framework/role__formAuthUser";
	}
	
	@RequestMapping({"treeData"})
	@ResponseBody
	public List<SysRole> treeData(String userType, Boolean isAll, String isShowCode, String ctrlPermi) {
		
		SysRole row = new SysRole();
		List<SysRole> list = modelService.template(row);
		return list;
	}
	
	@RequestMapping({"formAuthDataScope"})
	public String formAuthDataScope(SysRole row, Model model, HttpServletRequest request) {
		Map<String, Object> paras = new HashMap<String, Object>();
		
		String roleCode = row.getRoleCode();
		row = modelService.single(roleCode);
		
		List<SysRoleDataScope> roleDataScopeList = Collections.EMPTY_LIST;
		if(row!=null && !StrUtil.isBlank(row.getRoleCode())) {
			paras.put("roleCode", row.getRoleCode());
			roleDataScopeList = modelService.execute(
				"select"
				+" t1.*"
				+" from sys_role_data_scope t1"
				+" where t1.role_code=#roleCode#"
				, SysRoleDataScope.class
				, paras
			);
		}
		
		model.addAttribute("row", row);
		model.addAttribute("roleDataScopeList", roleDataScopeList);
		return "modules/framework/role__formAuthDataScope";
	}
	
	@PostMapping({"formAuthDataScope"})
	@ResponseBody
	public Result formAuthDataScope(SysRole row, String op, HttpServletRequest request) {
		modelService.saveAuthDataScope(row);
		return Result.SUCCESS;
	}
	
	@PostMapping({ "delete" })
	@ResponseBody
	public Result delete(SysRole row) {
		Map<String, Object> paras = new HashMap<>();
		if(row!=null) {
			String pk = row.getRoleCode();
			SysRole sysRole = modelService.single(pk);
			if(null==sysRole) {
				return Result.error("[0]无效编码！");
			}
			if("1".equals(sysRole.getIsSys())) {
				return Result.error("[1]系统内置角色，不可删除！");
			}
			List<Map> userRoleList = Collections.EMPTY_LIST;
			if(StrUtil.isNotBlank(pk)) {
				paras.put("roleCode", pk);
				userRoleList = modelService.execute(
					"select"
					+" t1.*"
					+" from sys_user_role t1"
					+" where t1.role_code=#roleCode#"
					, Map.class
					, paras
				);
			}
			if(userRoleList.size()>0) {
				return Result.error("该角色下有授权用户，请取消后再删除该角色！");
			}
			modelService.deleteById(pk);
		}
		return Result.SUCCESS;
	}
	
	@PostMapping({ "deleteAuthUser" })
	@ResponseBody
	public Result deleteAuthUser(String roleCode,String userRoleString) {		
		if(StrUtil.isBlank(roleCode)) {
			return Result.error("[0]角色编号为空！");			
		}
		if(StrUtil.isBlank(userRoleString)) {
			return Result.error("[0]用户编号为空！");			
		}
		String[] userCodes=userRoleString.split(",");
		for (String userCode : userCodes) {
			SysUser sysUser = userService.single(userCode);
			if(null ==sysUser) {
				return Result.error("[1]用户编号:"+userCode+",不存在！请刷新页面重新选择");	
			}
		}
		
		SysRole sysRole = modelService.single(roleCode);
		if(null ==sysRole) {
			return Result.error("[1]角色编号不存在！");	
		}
		try {
			modelService.deleteAuthUser(roleCode, userRoleString);		
			return Result.SUCCESS;
		}catch (Exception e) {
			return Result.error("[99]系统异常！");
		}
		
	}
	
}
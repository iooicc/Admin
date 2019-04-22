package my.admin.framework.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.hutool.core.util.ImageUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import my.admin.core.controller.BaseController;
import my.admin.dto.Page;
import my.admin.framework.service.RoleService;
import my.admin.framework.service.UserService;
import my.admin.framework.util.UserUtil;
import my.admin.model.SysAdmin;
import my.admin.model.SysRole;
import my.admin.model.SysUser;
import my.common.config.Global;
import my.common.beans.Result;

@Controller
@RequestMapping({ "/user" })
public class UserController extends BaseController {
	
	@Autowired
	UserService modelService;
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value = "index")
	public String index(SysUser empUser, Model model) {
		return "modules/framework/user__index";
	}
	
	@RequestMapping({ "list" })
	public String list(SysUser row, Model model) {
		model.addAttribute("row", row);
		return "modules/framework/user__list";
	}
	
	@RequestMapping({ "listData" })
	@ResponseBody
	public Page listData(
		SysUser row,
		@RequestParam(defaultValue="1") Long pageNo
	) {
		SysUser user = UserUtil.getUser();
		String corpCode = user.getCorpCode();
		//内置管理员能查看所有人员，不需要数据过滤
		if(!"0".equals(user.getMgrType())) {
			row.setCorpCode(corpCode);
		}		
		PageQuery<SysUser> page = new PageQuery<SysUser>(pageNo, 20, row);
		modelService.page(page);
		
		return new Page(page);
	}
	
	@RequestMapping(value = "form")
	public String form(SysUser row, String op, Model model) {
		
		String userCode = row.getUserCode();
		if(StrUtil.isBlank(userCode)) {
			
		} else {
			row = modelService.single(userCode);
			row.setPassword("");
			
			// 获取当前编辑用户的角色和权限
			if (StrUtil.containsAny(op, "auth")) {
				// 获取当前用户所拥有的角色
				List<SysRole> roleList = roleService.findListByUserCode(userCode);
				model.addAttribute("roleList", roleList);
			}
		}
		
		// 操作类型：add: 全部； edit: 编辑； auth: 授权
		model.addAttribute("op", op);
		model.addAttribute("row", row);
		return "modules/framework/user__form";
	}
	
	@PostMapping(value = "save")
	@ResponseBody
	public Result save(SysUser row, String op) {
		modelService.save(row);
		return Result.SUCCESS;
	}
	
	@PostMapping({ "delete" })
	@ResponseBody
	public Result delete(SysUser row) {
		if(row!=null) {
			String pk = row.getUserCode();
			modelService.deleteById(pk);
		}
		return Result.SUCCESS;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "disable")
	public Result disable(SysUser row) {
		SysUser obj = new SysUser();
		obj.setUserCode(row.getUserCode());
		obj.setStatus(SysUser.STATUS_DISABLE);
		modelService.updateTemplateById(obj);
		return Result.SUCCESS;
	}
	
	@ResponseBody
	@RequestMapping(value = "enable")
	public Result enable(SysUser row) {
		SysUser obj = new SysUser();
		obj.setUserCode(row.getUserCode());
		obj.setStatus(SysUser.STATUS_NORMAL);
		modelService.updateTemplateById(obj);
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
	
	@RequestMapping({"info"})
	public String info(SysUser row, String op, Model model) {
		if (StrUtil.isBlank(op)) {
			op = "base";
		}
		SysUser user = UserUtil.getUser();
		
		model.addAttribute("op", op);
		model.addAttribute("user", user);
		return "modules/framework/user__info";
	}
	
	@PostMapping({"infoSaveBase"})
	@ResponseBody
	public Result infoSaveBase(SysUser row, HttpServletRequest request) throws IOException {
		
		Result result = new Result();
		if(StrUtil.isBlank(row.getUserName())) {
			return result.setError("用户名称不能为空.");
		}
		
		SysUser user = UserUtil.getUser();
		String userCode = user.getUserCode();
		String avatar = String.format("avatar/%s.jpg", userCode);
		
		String base64 = row.getAvatarBase64();
		if(!StrUtil.isBlank(base64)) {
			
			base64 = StrUtil.subAfter(base64, ",", false);
			
			File targetFile = new File(
				Global.getProperty("project.upload.savePath")
				,avatar
			);
			
			if(!targetFile.exists()) {
				targetFile.createNewFile();
			}
			
			BufferedImage image = ImageUtil.toImage(base64);
			ImageUtil.write(image, targetFile);
		}
		
		user.setUserName(row.getUserName());
		user.setAvatar(avatar);
		user.setEmail(row.getEmail());
		user.setMobile(row.getMobile());
		user.setPhone(row.getPhone());
		user.setSex(row.getSex());
		user.setSign(row.getSign());
		
		modelService.updateTemplateById(user);
		return Result.SUCCESS;
	}
	
	@PostMapping({"infoSavePwd"})
	@ResponseBody
	public Result infoSavePwd(SysUser row, String oldPassword, String newPassword, String confirmNewPassword) {
		Result result = new Result();
		SysUser user = UserUtil.getUser();
		String userCode = user.getUserCode();
		
		if(!StrUtil.equals(newPassword, confirmNewPassword)) {
			return result.setError("两次密码输入不一致.");
		} else if(!modelService.validatePassword(oldPassword, userCode)) {
			return result.setError("旧密码不正确.");
		}
		
		modelService.updatePassword(newPassword, userCode);
		
		return Result.SUCCESS;
	}

	@PostMapping({"resetpwd"})
	@ResponseBody
	public Result resetpwd(SysUser row, String newPassword, String confirmNewPassword) {

		String userCode = row.getUserCode();
		if(StrUtil.isBlank(userCode)) {
			return Result.error("[0]用户编码不存在");
		}
		SysUser single = modelService.single(userCode);
		if(null ==single){
			return Result.error("[1]用户编码不存在");
		}
		if(StrUtil.isBlank(newPassword) || StrUtil.isBlank(confirmNewPassword)) {
			newPassword="111111";
			confirmNewPassword="111111";
		}
		if(!StrUtil.equals(newPassword, confirmNewPassword)) {
			return Result.error("[2]两次密码输入不一致.");
		}		
		modelService.updatePassword(newPassword, userCode);
		
		return Result.SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
}

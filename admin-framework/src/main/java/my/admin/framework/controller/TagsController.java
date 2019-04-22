package my.admin.framework.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import my.common.web.http.ServletUtil;

@Controller
@RequestMapping({ "/tags" })
public class TagsController {
	
	@RequestMapping({ "treeselect" })
	public String treeselect() {
		return "modules/framework/tags__treeselect";
	}
	
	@RequestMapping({ "iconselect" })
	public String iconselect() {
		return "modules/framework/tags__iconselect";
	}
	
	@RequestMapping({"imageclip"})
	public String imageclip(HttpServletRequest request, Model model) {
		model.addAllAttributes(ServletUtil.getParameters(request));
		return "modules/framework/tags__imageclip";
	}

}

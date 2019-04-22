package my.admin.framework.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.ActionEnter;

import my.common.config.Global;

@Controller
@RequestMapping({ "/ueditor" })
public class UeditorController {
	
	@RequestMapping({"controller"})
	@ResponseBody
	public String upload(String action, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, JSONException {
		
		request.setCharacterEncoding("utf-8");
		response.setHeader("Content-Type", "text/html");
		
		String rootPath = Global.getProperty("project.upload.savePath");
		return new ActionEnter( request, rootPath ).exec();
	}

}

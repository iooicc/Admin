package my.admin.framework.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hutool.core.util.StrUtil;

@Controller
public class ValidCodeController {
	
	public static final String VALID_CODE = "validCode";
	
	@RequestMapping({"/validCode"})
	public void validCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String validCode = request.getParameter("validCode");
		if(StrUtil.isNotBlank(validCode)) {
			String sValidCode = (String) request.getSession().getAttribute(VALID_CODE);
			boolean valid = sValidCode != null && sValidCode.equalsIgnoreCase(validCode);
			request.getSession().removeAttribute(validCode);
			response.getOutputStream().print(valid ? "true" : "false");
		} else {
			
		}
	}
}

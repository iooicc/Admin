package my.admin.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected static final String REDIRECT = "redirect:";
	
	@Value("${project.context-path:''}")
	protected String contextPath;
}

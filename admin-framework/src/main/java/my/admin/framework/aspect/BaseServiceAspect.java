package my.admin.framework.aspect;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import cn.hutool.core.bean.BeanUtil;
import my.admin.core.model.BaseModel;
import my.admin.framework.util.UserUtil;
import my.admin.model.SysUser;

@Aspect
@Component
public class BaseServiceAspect {
	
	public BaseServiceAspect() {
	}
	
	@Pointcut("execution(* my..*.service.*.insert*(..))")
	private void baseServiceInsert() {
	}
	
	@Pointcut("execution(* my..*.service.*.save*(..))")
	private void baseServiceSave() {
	}
	
	@Pointcut("execution(* my..*.service.*.update*(..))")
	private void baseServiceUpdate() {
	}

	@Before(value = "baseServiceInsert() || baseServiceSave()")
	public void beforeInsert(JoinPoint p) {
		Object[] args = p.getArgs();
		if(args==null) {
			return;
		}
		Date now = new Date();
		for(Object arg:args) {
			if(!(arg instanceof BaseModel)) {
				continue;
			}
			
			try {
				BeanUtil.setFieldValue(arg, "createDate", now);
				BeanUtil.setFieldValue(arg, "updateDate", now);
				SysUser user = UserUtil.getUser();
				
				if(user!=null) {
					BeanUtil.setFieldValue(arg, "createBy", user.getUserCode());
					BeanUtil.setFieldValue(arg, "updateBy", user.getUserCode());
					BeanUtil.setFieldValue(arg, "corpCode", user.getCorpCode());
				}
			} catch (Exception e) {
			}
		}
	}
	
	@Before(value = "baseServiceUpdate()")
	public void beforeUpdate(JoinPoint p) {
		Object[] args = p.getArgs();
		if(args==null) {
			return;
		}
		Date now = new Date();
		for(Object arg:args) {
			if(!(arg instanceof BaseModel)) {
				continue;
			}
			
			try {
				BeanUtil.setFieldValue(arg, "updateDate", now);
				SysUser user = UserUtil.getUser();
				
				if(user!=null) {
					BeanUtil.setFieldValue(arg, "updateBy", user.getUserCode());
				}
			} catch (Exception e) {
			}
		}
	}
}

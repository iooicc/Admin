package my.admin.beetl.ext.fn;

import org.beetl.core.Context;
import org.beetl.core.Function;

import cn.hutool.core.bean.BeanUtil;

public class GetProperty implements Function {

	public Object call(Object[] paras, Context ctx) {
		
		Object bean = paras.length>0?paras[0]:null;
		if(bean==null) {
			return null;
		}
		
		String expression = paras.length>1?(String) paras[1]:null;
		if(expression==null) {
			return null;
		}
		
		Object result = BeanUtil.getProperty(bean, expression);
		
		return result;
	}

}

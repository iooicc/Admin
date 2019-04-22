package my.admin.beetl.ext.fn;

import java.util.Collection;

import org.beetl.core.Context;
import org.beetl.core.Function;

import cn.hutool.core.util.StrUtil;

public class IsBlank implements Function {

	public Object call(Object[] paras, Context ctx) {
		Object obj = paras[0];
		
		if(obj == null) {
			return true;
		}
		
		if(obj instanceof String) {
			return StrUtil.isBlank((String) obj);
		}
		
		if(obj instanceof Collection) {
			return ((Collection) obj).size() == 0;
		}
		
		return false;
	}

}

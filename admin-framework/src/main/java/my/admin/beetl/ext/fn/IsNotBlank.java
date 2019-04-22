package my.admin.beetl.ext.fn;

import org.beetl.core.Context;
import org.beetl.core.Function;

public class IsNotBlank implements Function {
	
	IsBlank fn = new IsBlank();

	public Object call(Object[] paras, Context ctx) {
		Object result = fn.call(paras, ctx);
		if(result instanceof Boolean) {
			return !(Boolean) result;
		}
		return false;
	}

}

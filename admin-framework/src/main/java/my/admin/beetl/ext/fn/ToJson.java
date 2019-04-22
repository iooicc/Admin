package my.admin.beetl.ext.fn;

import org.beetl.core.Context;
import org.beetl.core.Function;

import cn.hutool.json.JSONUtil;

public class ToJson implements Function {
	public Object call(Object[] paras, Context ctx) {
		if(paras==null
			|| paras[0] == null
		) {
			return "";
		}
		return JSONUtil.toJsonStr(paras[0]);
	}
}
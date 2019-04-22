package my.admin.beetl.ext.fn;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beetl.core.Context;
import org.beetl.core.Function;

import cn.hutool.core.util.StrUtil;

public class Cookie implements Function {
	public Object call(Object[] paras, Context ctx) {
		String name =  paras.length >= 1 ? (String) paras[0] : null;
		boolean isRemove =  paras.length >= 2 ? (Boolean) paras[1] : false;
		String path = paras.length >= 3 ? (String) paras[2] : null;
		
		HttpServletRequest request = (HttpServletRequest) ctx.getGlobal("request");
		HttpServletResponse response = (HttpServletResponse) ctx.getGlobal("response");
		
		String value = null;
		if (StrUtil.isNotBlank(name)){
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (javax.servlet.http.Cookie cookie : cookies) {
					if (cookie.getName().equals(name)) {
						try {
							value = URLDecoder.decode(cookie.getValue(), "utf-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						if (isRemove && response != null) {
							cookie.setPath(path);
							cookie.setMaxAge(0);
							response.addCookie(cookie);
						}
					}
				}
			}
		}
		return value;
	}
}

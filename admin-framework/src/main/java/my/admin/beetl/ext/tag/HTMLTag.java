package my.admin.beetl.ext.tag;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.beetl.core.BodyContent;
import org.beetl.core.Context;
import org.beetl.core.Template;
import org.beetl.core.statement.Statement;
import org.beetl.ext.tag.HTMLTagSupportWrapper;

import cn.hutool.core.util.StrUtil;

public class HTMLTag extends HTMLTagSupportWrapper {
	private HttpServletRequest request;
	private String tagName;
	private LinkedList<HTMLTag> tags;
	
	private String dataKey(String key) {
		if(!StrUtil.startWith(key, "data")) {
			return key;
		}
		
		char[] chars = key.toCharArray();
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			if(Character.isUpperCase(chars[i])){
				temp.append("-");
			}
			temp.append(Character.toLowerCase(chars[i]));
		}
		return temp.toString();
	}

	public void init(Context ctx, Object[] args, Statement st) {
		super.init(ctx, args, st);
		this.request = (HttpServletRequest) this.ctx.getGlobal("request");
	}
	
	public Map<String, Object> getAttrs() {
		return (Map) (this.args.length >= 2 ? this.args[1] : new HashMap());
	}
	
	public String getExts() {
		Map attrs = getAttrs();
		Iterator<String> ite = attrs.keySet().iterator();
		StringBuffer buf = new StringBuffer();
		while(ite.hasNext()) {
			String key = ite.next();
			Object value = attrs.get(key);
			
			key = dataKey(key);
			
			if(!"path".equals(key)
				&& !"id".equals(key)
				&& !"name".equals(key)
				&& !"value".equals(key)
				&& !"readonly".equals(key)
			) {
				buf.append(" ").append(key).append("=\"").append(value).append("\"");
			}
		}
		return buf.toString();
	}
	
	public HTMLTag getParentByTagName(String tagName) {
		for(int i=this.tags.size()-1;i>=0;i--) {
			HTMLTag tag = this.tags.get(i);
			if(tag.tagName!=null && tag.tagName.equals(tagName)) {
				return tag;
			}
		}
		return null;
	}
	
	public void render() {
		this.tags = (LinkedList) this.request.getAttribute("tags");
		if (this.tags == null) {
			this.tags = new LinkedList<HTMLTag>();
			this.request.setAttribute("tags", this.tags);
		}

		this.tags.add(this);
		super.render();
	}
	
	protected void callHtmlTag(String path)
	{
		this.tagName = StrUtil.subAfter((String) this.args[0], ":", false);
		
		Template t = null;

		t = gt.getHtmlFunctionOrTagTemplate(path, this.ctx.getResourceId());

		t.binding(ctx.globalVar);
		t.dynamic(ctx.objectKeys);

		if (args.length == 2)
		{
			Map<String, Object> map = (Map<String, Object>) args[1];
			for (Entry<String, Object> entry : map.entrySet())
			{
				t.binding(entry.getKey(), entry.getValue());

			}
		}

		t.binding("this", this);
		BodyContent bodyContent = super.getBodyContent();
		t.binding("tagBody", bodyContent);

		t.renderTo(ctx.byteWriter);
	}
}

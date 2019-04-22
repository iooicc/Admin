package my.admin.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import my.admin.core.model.BaseModel;

public class BaseModelUtil {
	
	public static <K> Map getMap_Map(List<? extends BaseModel> list, String field) {
		Map<Object, BaseModel> result = new HashMap<Object, BaseModel>();
		for(int i=0;i<list.size();i++) {
			BaseModel ii = list.get(i);
			Object key = BeanUtil.getProperty(ii, field);
			
			result.put(key, ii);
		}
		return result;
	}
	
	public static <K> Map getMap_List(List<? extends BaseModel> list, String field) {
		Map<Object, List<? extends BaseModel>> result = new HashMap<Object, List<? extends BaseModel>>();
		for(int i=0;i<list.size();i++) {
			BaseModel ii = list.get(i);
			Object key = BeanUtil.getProperty(ii, field);
			List tempList = result.get(key);
			if(tempList==null) {
				tempList = new ArrayList();
				result.put(key, tempList);
			}
			tempList.add(ii);
		}
		return result;
	}

	public static String getIn(List<? extends BaseModel> list, String field) {
		if(null==list||list.size()==0||null==field||"".equals(field)) {
			return "";
		}
		
		Set<String> items = CollUtil.newHashSet();
		for(int i=0;i<list.size();i++) {
			BaseModel ii = list.get(i);
			Object value = BeanUtil.getProperty(ii, field);
			items.add(String.format("'%s'", value));
		}
		
		String in = "("+CollUtil.join(items, ",")+")";
		return in;
	}
	
	public static String getInBank(List<? extends BaseModel> list, String field) {
		if(null==list||list.size()==0||null==field||"".equals(field)) {
			return "('')";
		}
		
		Set<String> items = CollUtil.newHashSet();
		for(int i=0;i<list.size();i++) {
			BaseModel ii = list.get(i);
			Object value = BeanUtil.getProperty(ii, field);
			items.add(String.format("'%s'", value));
		}
		
		String in = "("+CollUtil.join(items, ",")+")";
		return in;
	}
	
}

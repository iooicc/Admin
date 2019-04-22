package my.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.bean.BeanUtil;

public class MyMapUtil {
	
	public static <K, V> Map<K, V> getMap_Key(List<V> list, String field) {
		Map<K, V> result = new HashMap<K, V>();
		try {
			for(int i=0;i<list.size();i++) {
				V ii = list.get(i);
				K key = (K) BeanUtil.getProperty(ii, field);
				result.put(key, ii);
			}
		} catch (Exception e) {
		}
		return result;
	}
}

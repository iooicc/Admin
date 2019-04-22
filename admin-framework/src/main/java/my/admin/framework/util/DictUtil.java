package my.admin.framework.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import my.admin.core.utils.BaseModelUtil;
import my.admin.framework.service.DictDataService;
import my.admin.model.SysDictData;
import my.common.cache.CacheUtil;
import my.common.utils.SpringUtil;

public class DictUtil {
	
	private static DictDataService dictDataService = (DictDataService) SpringUtil.getBean(DictDataService.class);
	
	
	public static void clearCache() {
		CacheUtil.remove("dictMap");
	}
	
	public static List<SysDictData> getDictList(String dictType) {
		
		Map<String, List<SysDictData>> dictMap = (Map) CacheUtil.get("dictMap");
		if(dictMap==null) {
			List<SysDictData> list = dictDataService.all();
			dictMap = BaseModelUtil.getMap_List(list, "dictType");
			CacheUtil.put("dictMap", dictMap);
		}
		
		List list = dictMap.get(dictType);
		if(list==null) {
			list = new ArrayList();
		}
		
		return list;
	}
	
	public static String getDictListJson(String dictType) {
		return JSONUtil.toJsonStr(getDictList(dictType));
	}
	
	public static String getDictLabel(String dictType, String dictValue, String defaultValue) {
		if (StrUtil.isNotBlank(dictType) && StrUtil.isNotBlank(dictValue)) {
			Iterator<SysDictData> ite = getDictList(dictType).iterator();

			while (ite.hasNext()) {
				SysDictData ii = (SysDictData) ite.next();
				if (dictType.equals(ii.getDictType()) && dictValue.equals(ii.getDictValue())) {
					return ii.getDictLabel();
				}
			}
		}
		return defaultValue;
	}
	
}

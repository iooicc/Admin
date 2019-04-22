package my.common.cache;

import java.util.Iterator;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import cn.hutool.core.util.StrUtil;
import my.common.utils.SpringUtil;

public class CacheUtil {
	private static CacheManager cacheManager = SpringUtil.getBean(CacheManager.class);
	
	private static final String SYS_CACHE = "sysCache";
	
	public static Object get(String key) {
		return get(SYS_CACHE, key);
	}
	
	public static Object get(String cacheName, String key) {
		return getCache(cacheName).get(key);
	}
	
	public static void put(String key, Object value) {
		put(SYS_CACHE, key, value);
	}
	
	public static void put(String cacheName, String key, Object value) {
		getCache(cacheName).put(key, value);
	}
	
	public static Cache<String, Object> getCache(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		if(cache==null) {
			throw new RuntimeException("cache["+cacheName+"] not found.");
		} else {
			return cache;
		}
	}
	
	public static void remove(String cacheName, String key) {
		getCache(cacheName).remove(key);
	}
	
	public static void remove(String key) {
		remove("sysCache", key);
	}
	
	public static void removeAll(String cacheName) {
		Cache cache = getCache(cacheName);
		Iterator ite = cache.keys().iterator();
		while(ite.hasNext()) {
			Object key = ite.next();
			cache.remove(key);
		}
	}
	
	public static void removeByKeyPrefix(String cacheName, String keyPrefix) {
		Cache cache = getCache(cacheName);
		Iterator ite = cache.keys().iterator();
		while(ite.hasNext()) {
			Object key = ite.next();
			String keyString = (String) key;
			if(StrUtil.startWith(keyString, keyPrefix)) {
				cache.remove(key);
			}
		}
	}
}

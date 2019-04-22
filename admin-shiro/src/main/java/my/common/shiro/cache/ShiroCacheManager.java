package my.common.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import net.sf.ehcache.Ehcache;

public class ShiroCacheManager implements org.apache.shiro.cache.CacheManager {
	
	protected net.sf.ehcache.CacheManager cacheManager;
	
	public ShiroCacheManager(net.sf.ehcache.CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	public net.sf.ehcache.CacheManager getCacheManager() {
		return this.cacheManager;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
		try {
			Ehcache cache = this.getCacheManager().getEhcache(cacheName);
			if (cache == null) {
				this.getCacheManager().addCache(cacheName);
				cache = this.getCacheManager().getCache(cacheName);
			}

			return new ShiroCache(cache);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

}

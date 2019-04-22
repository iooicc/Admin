package my.common.shiro.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class ShiroCache<K, V> implements Cache<K, V> {
	
	private Ehcache ehcache;
	
	public ShiroCache(Ehcache ehcache) {
		this.ehcache = ehcache;
	}

	@Override
	public void clear() throws CacheException {
		try {
			this.ehcache.removeAll();
		} catch (Throwable e) {
			throw new CacheException(e);
		}
	}

	@Override
	public V get(K key) throws CacheException {
		try {
			if (key == null) {
				return null;
			} else {
				Element element = ehcache.get(key);
				if (element == null) {
					return null;
				} else {
					return (V) element.getObjectValue();
				}
			}
		} catch (Throwable e) {
			throw new CacheException(e);
		}
	}

	@Override
	public Set<K> keys() {
		try {
			List<K> keys = ehcache.getKeys();
			if(keys!=null) {
				return Collections.unmodifiableSet(new LinkedHashSet(keys));
			}
			return Collections.emptySet();
		} catch (Throwable e) {
			throw new CacheException(e);
		}
	}

	@Override
	public V put(K key, V value) throws CacheException {
		try {
			
			V previous = this.get(key);
			
			Element element = new Element(key, value);
			this.ehcache.put(element);
			return previous;
		} catch (Throwable e) {
			throw new CacheException(e);
		}
	}

	@Override
	public V remove(K key) throws CacheException {
		try {
			V value = this.get(key);
			this.ehcache.remove(key);
			return value;
		} catch (Throwable e) {
			throw new CacheException(e);
		}
	}

	@Override
	public int size() {
		try {
			return this.ehcache.getSize();
		} catch (Throwable e) {
			throw new CacheException(e);
		}
	}

	@Override
	public Collection<V> values() {
		try {
			List<V> values = null;
			List<K> keys = this.ehcache.getKeys();
			if(keys!=null) {
				values = new ArrayList<V>(keys.size());
				Iterator<K> ite = keys.iterator();
				while(ite.hasNext()) {
					K key = ite.next();
					V value = this.get(key);
					if(value!=null) {
						values.add(value);
					}
				}
				return Collections.unmodifiableList(values);
			} else {
				return Collections.emptyList();
			}
		} catch (Throwable e) {
			throw new CacheException(e);
		}
	}
	
}
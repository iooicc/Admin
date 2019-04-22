package my.admin.core.service;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.beetl.sql.core.RowMapper;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import cn.hutool.core.bean.BeanUtil;
import my.admin.core.dao.BaseDao;
import my.admin.core.exception.BusinessException;
import my.admin.core.model.BaseModel;

public abstract class BaseServiceImpl<T extends BaseModel, DAO extends BaseDao<T>> implements BaseService<T> {
	
	@Autowired
	protected SQLManager sqlManager;
	
	@Autowired
	protected DAO dao;
	
	protected BusinessException exception(String msg) {
		return new BusinessException(msg);
	}
	
	private Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
	private void onInsert(T obj) {}
	
	private void onUpdate(T obj) {}
	
	public int insert(T paras) {
		onInsert(paras);
		return sqlManager.insert(paras);
	}

	public int insertTemplate(T paras) {
		onInsert(paras);
		return sqlManager.insertTemplate(paras);
	}

	public int insertTemplate(T paras, boolean autoDbAssignKey) {
		onInsert(paras);
		return sqlManager.insertTemplate(paras, autoDbAssignKey);
	}

	public <U extends BaseModel> int insertTemplate(Class<U> clazz, Object paras) {
		return sqlManager.insertTemplate(clazz, paras, false);
	}

	public <U extends BaseModel> int insertTemplate(Class<U> clazz, Object paras, boolean autoDbAssignKey) {
		return sqlManager.insertTemplate(clazz, paras, autoDbAssignKey);
	}
	
	public <U extends BaseModel> int[] insertBatch(Class<U> clazz, List<U> list) {
		return sqlManager.insertBatch(clazz, list);
	}
	
	public void insertBatch(List<T> list) {
		dao.insertBatch(list);
	}
	
	/* 刪 */
	public int deleteById(Object pk) {
		return dao.deleteById(pk);
	}
	public <U extends BaseModel> int deleteById(Class<U> clazz, Object pk) {
		return sqlManager.deleteById(clazz, pk);
	}
	/* 改 */
	public int updateTemplateById(T obj) {
		onUpdate(obj);
		return sqlManager.updateTemplateById(obj);
	}
	public <U extends BaseModel> int updateTemplateById(Class<U> clazz, Map paras) {
		return sqlManager.updateTemplateById(clazz, paras);
	}
	
	public <U extends BaseModel> int updateTemplateById(Class<U> clazz, Object paras) {
		return sqlManager.updateTemplateById(clazz, paras);
	}
	
	public <U extends BaseModel> int[] updateBatchTemplateById(Class<U> clazz, List<?> list) {
		return sqlManager.updateBatchTemplateById(clazz, list);
	}
	/* 查 */
	public List<T> all() {
		return dao.all();
	}
	public List<T> template(T t) {
		return sqlManager.template(t);
	}
	public List<T> template(T t, RowMapper<T> mapper) {
		return sqlManager.template(t, mapper);
	}
	public T single(Object key) {
		return dao.single(key);
	}
	public <U extends BaseModel> U single(Class<U> clazz, Object pk) {
		return sqlManager.single(clazz, pk);
	}
	public Query<T> query() {
		return dao.createQuery();
	}
	public long templateCount(T t) {
		return sqlManager.templateCount(t);
	}
	public long templateCount(Class<? extends BaseModel> clazz, Object obj) {
		return sqlManager.templateCount(clazz, obj);
	}
	public long count(String sqlTemplate) {
		return count(sqlTemplate, Collections.EMPTY_MAP);
	}
	public long count(String sqlTemplate, Map paras) {
		List<Long> rows = execute(sqlTemplate, Long.class, paras);
		if(rows==null||rows.size()==0) {
			return 0L;
		}
		return rows.get(0);
	}
	
	/* execute */
	public List<T> execute(String sqlTemplate, Object paras) {
		return execute(sqlTemplate, getTClass(), paras);
	}
	public List<T> execute(String sqlTemplate, Map paras) {
		return execute(sqlTemplate, getTClass(), paras);
	}
	public T executeSingle(String sqlTemplate, Map paras) {
		List<T> list = execute(sqlTemplate, paras);
		if(list==null || list.size()==0) {
			return null;
		}
		return list.get(0);
	}
	public <U> List<U> execute(String sqlTemplate, Class<U> clazz, Object paras) {
		return sqlManager.execute(sqlTemplate, clazz, paras);
	}
	public <U> List<U> execute(String sqlTemplate, Class<U> clazz, Map paras) {
		return sqlManager.execute(sqlTemplate, clazz, paras);
	}
	public <U> U executeSingle(String sqlTemplate, Class<U> clazz, Map paras) {
		List<U> list = execute(sqlTemplate, clazz, paras);
		if(list==null || list.size()==0) {
			return null;
		}
		return list.get(0);
	}
	public int executeUpdate(String sqlTemplate, Map paras) {
		return sqlManager.executeUpdate(sqlTemplate, paras);
	}
	
	/* sqlId */
	public <U> List<U> select(String sqlId, Class<U> clazz, Map paras) {
		return sqlManager.select(sqlId, clazz, paras);
	}
	public <U> List<U> select(String sqlId, Class<U> clazz, Object paras) {
		return sqlManager.select(sqlId, clazz, paras);
	}
	
	/* 分页 */
	public void templatePage(PageQuery<T> query) {
		dao.templatePage(query);
	}
	public <U> PageQuery<U> pageQuery(String sqlId, Class<U> clazz, PageQuery<U> query) {
		return sqlManager.pageQuery(sqlId, clazz, query);
	}
}
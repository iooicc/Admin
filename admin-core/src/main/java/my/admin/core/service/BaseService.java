package my.admin.core.service;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.RowMapper;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.query.Query;

import my.admin.core.model.BaseModel;

public interface BaseService<T extends BaseModel> {

	/* 增 */
	int insert(T paras);
	int insertTemplate(T paras);
	int insertTemplate(T paras, boolean autoDbAssignKey);
	
	<U extends BaseModel> int insertTemplate(Class<U> clazz, Object paras);
	<U extends BaseModel> int insertTemplate(Class<U> clazz, Object paras, boolean autoDbAssignKey);
	<U extends BaseModel> int[] insertBatch(Class<U> clazz, List<U> list);
	void insertBatch(List<T> list);
	/* 刪 */
	int deleteById(Object pk);
	<U extends BaseModel> int deleteById(Class<U> clazz, Object pk);
	/* 改 */
	int updateTemplateById(T obj);
	<U extends BaseModel> int updateTemplateById(Class<U> clazz, Map paras);
	<U extends BaseModel> int updateTemplateById(Class<U> clazz, Object paras);
	<U extends BaseModel> int[] updateBatchTemplateById(Class<U> clazz, List<?> list);
	/* 查 */
	List<T> all();
	List<T> template(T t);
	List<T> template(T t, RowMapper<T> mapper);
	T single(Object key);
	<U extends BaseModel> U single(Class<U> clazz, Object pk);
	Query<T> query();
	
	long templateCount(T t);
	long templateCount(Class<? extends BaseModel> clazz, Object obj);
	long count(String sqlTemplate);
	long count(String sqlTemplate, Map paras);
	/* execute */
	List<T> execute(String sqlTemplate, Object paras);
	List<T> execute(String sqlTemplate, Map paras);
	T executeSingle(String sqlTemplate, Map paras);
	
	<U> List<U> execute(String sqlTemplate, Class<U> clazz, Object paras);
	<U> List<U> execute(String sqlTemplate, Class<U> clazz, Map paras);
	<U> U executeSingle(String sqlTemplate, Class<U> clazz, Map paras);
	
	int executeUpdate(String sqlTemplate, Map paras);
	/* sqlId */
	<U> List<U> select(String sqlId, Class<U> clazz, Map paras);
	<U> List<U> select(String sqlId, Class<U> clazz, Object paras);
	
	/* 分页 */
	void templatePage(PageQuery<T> query);
	<U> PageQuery<U> pageQuery(String sqlId, Class<U> clazz, PageQuery<U> query);
	
}
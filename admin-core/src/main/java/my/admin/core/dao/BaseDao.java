package my.admin.core.dao;

import org.beetl.sql.core.mapper.BaseMapper;

import my.admin.core.model.BaseModel;

public interface BaseDao<T extends BaseModel> extends BaseMapper<T> {

}

package com.jhy.plateform.dao;

import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.MaterialType;
import com.jhy.plateform.query.MaterialTypeQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MaterialTypeMapper extends BaseDao<MaterialType, MaterialTypeQuery> {
}

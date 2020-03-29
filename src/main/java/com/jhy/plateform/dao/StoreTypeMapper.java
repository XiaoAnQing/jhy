package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.StoreType;
import com.jhy.plateform.query.StoreTypeQuery;
import com.jhy.plateform.domain.StoreType;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface StoreTypeMapper extends BaseDao<StoreType,StoreTypeQuery> {
}
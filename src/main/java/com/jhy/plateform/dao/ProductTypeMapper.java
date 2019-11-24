package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.ProductType;
import com.jhy.plateform.query.ProductTypeQuery;
import com.jhy.plateform.domain.ProductType;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ProductTypeMapper extends BaseDao<ProductType,ProductTypeQuery> {
}
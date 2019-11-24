package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.Product;
import com.jhy.plateform.query.ProductQuery;
import com.jhy.plateform.domain.Product;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ProductMapper extends BaseDao<Product,ProductQuery> {
}
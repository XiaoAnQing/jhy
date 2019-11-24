package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.Supplier;
import com.jhy.plateform.query.SupplierQuery;
import com.jhy.plateform.domain.Supplier;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface SupplierMapper extends BaseDao<Supplier,SupplierQuery> {
}
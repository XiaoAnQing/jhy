package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.Customer;
import com.jhy.plateform.query.CustomerQuery;
import com.jhy.plateform.domain.Customer;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface CustomerMapper extends BaseDao<Customer,CustomerQuery> {
}
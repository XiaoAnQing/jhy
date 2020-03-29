package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.Purchase;
import com.jhy.plateform.query.PurchaseQuery;
import com.jhy.plateform.domain.Purchase;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface PurchaseMapper extends BaseDao<Purchase,PurchaseQuery> {
}
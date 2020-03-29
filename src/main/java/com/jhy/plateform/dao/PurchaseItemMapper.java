package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.PurchaseItem;
import com.jhy.plateform.query.PurchaseItemQuery;
import com.jhy.plateform.domain.PurchaseItem;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface PurchaseItemMapper extends BaseDao<PurchaseItem,PurchaseItemQuery> {
}
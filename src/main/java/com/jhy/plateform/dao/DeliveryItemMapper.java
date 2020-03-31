package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.DeliveryItem;
import com.jhy.plateform.query.DeliveryItemQuery;
import com.jhy.plateform.domain.DeliveryItem;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface DeliveryItemMapper extends BaseDao<DeliveryItem,DeliveryItemQuery> {
}
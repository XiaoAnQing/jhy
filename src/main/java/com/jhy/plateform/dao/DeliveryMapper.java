package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.Delivery;
import com.jhy.plateform.query.DeliveryQuery;
import com.jhy.plateform.domain.Delivery;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface DeliveryMapper extends BaseDao<Delivery,DeliveryQuery> {
}
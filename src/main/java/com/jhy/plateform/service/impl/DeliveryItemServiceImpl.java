package com.jhy.plateform.service.impl;

import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.DeliveryItemMapper;
import com.jhy.plateform.domain.DeliveryItem;
import com.jhy.plateform.query.DeliveryItemQuery;
import com.jhy.plateform.service.DeliveryItemService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DeliveryItemServiceImpl extends BaseServiceImpl<DeliveryItem, DeliveryItemQuery> implements DeliveryItemService{

	@Autowired
	public void setDeliveryItemMapper(DeliveryItemMapper deliveryItemMapper){
		this.daoMapper = deliveryItemMapper;
	}
}
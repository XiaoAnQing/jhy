package com.jhy.plateform.service.impl;

import com.jhy.plateform.domain.*;
import com.jhy.plateform.service.*;
import com.jhy.plateform.utils.DateUtil;
import com.jhy.plateform.utils.StatusUtil;
import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.DeliveryMapper;
import com.jhy.plateform.query.DeliveryQuery;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DeliveryServiceImpl extends BaseServiceImpl<Delivery, DeliveryQuery> implements DeliveryService{

	@Autowired
	public void setDeliveryMapper(DeliveryMapper deliveryMapper){
		this.daoMapper = deliveryMapper;
	}


	@Autowired
	DeliveryItemService purchaseItemService;
	@Autowired
	MaterialService materialService;

	@Override
	public int add(Delivery delivery) {
		//准备数据
		delivery.setCreateDate(DateUtil.getCurrentDate());
		//状态
		//delivery.setStatus(StatusUtil.STATUS_PURCHASE_WAITING);

		BigDecimal price = BigDecimal.ZERO;

		List<DeliveryItem> purchaseItems = delivery.getDeliveryItems();



		for(DeliveryItem purchaseItem : purchaseItems) {
			purchaseItem.setDeliveryNum(delivery.getNum());
			purchaseItem.setLeftCount(purchaseItem.getTotalCount());
			Material material = materialService.findById(purchaseItem.getMaterialId()+"");
			purchaseItem.setName(material.getName());
			purchaseItem.setPrice(material.getPrice());
			purchaseItem.setStatus(StatusUtil.STATUS_DELIVERY_UNFINISH);

			purchaseItem.setTotalPrice(material.getPrice().multiply(new BigDecimal(purchaseItem.getTotalCount())));
			price = price.add(purchaseItem.getTotalPrice());
		}

		delivery.setPrice(price);
		int result =  daoMapper.add(delivery);


		for(DeliveryItem purchaseItem : purchaseItems) {
			//保存
			purchaseItemService.add(purchaseItem);
		}
		return result;
	}
}
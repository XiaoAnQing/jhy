package com.jhy.plateform.service.impl;

import com.jhy.plateform.domain.*;
import com.jhy.plateform.exception.ExceptionKind;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.PurchaseItemQuery;
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
	PurchaseItemService purchaseItemService;

	@Autowired
	public void setDeliveryMapper(DeliveryMapper deliveryMapper){
		this.daoMapper = deliveryMapper;
	}


	@Autowired
	DeliveryItemService deliveryItemService;
	@Autowired
	MaterialService materialService;

	@Override
	public int add(Delivery delivery) {
		//准备数据
		delivery.setCreateDate(DateUtil.getCurrentDate());
		//状态
		//delivery.setStatus(StatusUtil.STATUS_PURCHASE_WAITING);

		BigDecimal price = BigDecimal.ZERO;

		List<DeliveryItem> deliveryItems = delivery.getDeliveryItems();


		//修改送货单明细的数量 TODO ???


		for(DeliveryItem deliveryItem : deliveryItems) {
			deliveryItem.setDeliveryNum(delivery.getNum());
			deliveryItem.setLeftCount(deliveryItem.getTotalCount());
			Material material = materialService.findById(deliveryItem.getMaterialId()+"");
			deliveryItem.setName(material.getName());
			deliveryItem.setPrice(material.getPrice());
			deliveryItem.setStatus(StatusUtil.STATUS_DELIVERY_UNFINISH);

			deliveryItem.setTotalPrice(material.getPrice().multiply(new BigDecimal(deliveryItem.getTotalCount())));
			price = price.add(deliveryItem.getTotalPrice());
		}

		delivery.setPrice(price);
		int result =  daoMapper.add(delivery);


		for(DeliveryItem deliveryItem : deliveryItems) {
			//采购订单编号
			String purchaseNum = delivery.getPurchaseNum();
			Integer materialId = deliveryItem.getMaterialId();


			PurchaseItemQuery purchaseItemQuery = new PurchaseItemQuery();
			purchaseItemQuery.setPurchaseNum(purchaseNum);
			purchaseItemQuery.setMaterialId(materialId);
			purchaseItemQuery.setPagination(false);

			PurchaseItem oldPurchaseItem = purchaseItemService.findBySelective(purchaseItemQuery).getList().get(0);

			if(deliveryItem.getLeftCount()> oldPurchaseItem.getLeftCount()){
				throw new KPException(ExceptionKind.PARAM_E);
			}

			PurchaseItem purchaseItem = new PurchaseItem();
			purchaseItem.setLeftCount(oldPurchaseItem.getLeftCount()-deliveryItem.getTotalCount());
			purchaseItem.setId(oldPurchaseItem.getId());

			if(deliveryItem.getLeftCount()==oldPurchaseItem.getLeftCount()){
				//当前采购订单已经完成
				purchaseItem.setStatus(StatusUtil.STATUS_DELIVERY_FINISH);
			}

			purchaseItemService.updateById(purchaseItem);

			//保存
			deliveryItemService.add(deliveryItem);

		}
		return result;
	}
}
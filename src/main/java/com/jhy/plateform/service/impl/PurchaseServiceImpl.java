package com.jhy.plateform.service.impl;

import com.jhy.plateform.dao.PurchaseMapper;
import com.jhy.plateform.domain.*;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.PurchaseQuery;
import com.jhy.plateform.service.MaterialService;
import com.jhy.plateform.service.PurchaseItemService;
import com.jhy.plateform.service.PurchaseService;
import com.jhy.plateform.service.SupplierService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import com.jhy.plateform.utils.DateUtil;
import com.jhy.plateform.utils.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PurchaseServiceImpl extends BaseServiceImpl<Purchase, PurchaseQuery> implements PurchaseService{

	@Autowired
	public void setPurchaseMapper(PurchaseMapper purchaseMapper){
		this.daoMapper = purchaseMapper;
	}

	@Autowired
	PurchaseItemService purchaseItemService;
	@Autowired
	MaterialService materialService;

	@Autowired
	SupplierService supplierService;

	@Override
	public int add(Purchase purchase) {
		//准备数据
		purchase.setCreateDate(DateUtil.getCurrentDate());
		//状态
		purchase.setStatus(StatusUtil.STATUS_PURCHASE_WAITING);

		BigDecimal price = BigDecimal.ZERO;

		List<PurchaseItem> purchaseItems = purchase.getPurchaseItems();

		Supplier supplier = supplierService.findById(purchase.getSupplierId()+"");
		purchase.setSupplierName(supplier.getName());

		for(PurchaseItem purchaseItem : purchaseItems) {
			purchaseItem.setPurchaseNum(purchase.getNum());
			Material material = materialService.findById(purchaseItem.getMaterialId()+"");
			purchaseItem.setName(material.getName());
			purchaseItem.setPrice(material.getPrice());

			purchaseItem.setTotalPrice(material.getPrice().multiply(new BigDecimal(purchaseItem.getTotalCount())));
			price = price.add(purchaseItem.getTotalPrice());
		}

		purchase.setPrice(price);
		int result =  daoMapper.add(purchase);


		for(PurchaseItem purchaseItem : purchaseItems) {
			//保存
			purchaseItemService.add(purchaseItem);
		}
		return result;
	}



	@Override
	public int deleteById(String id) throws KPException {
		//判断状态
		Purchase purchase = daoMapper.findById(id);
		if(purchase==null || !purchase.getStatus().equals(StatusUtil.STATUS_PURCHASE_WAITING)){
			return -1;
		}
		return daoMapper.deleteById(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public int deleteByIds(String[] ids) {
		for(String id : ids){
			Purchase purchase = daoMapper.findById(id);
			if(purchase==null || !purchase.getStatus().equals(StatusUtil.STATUS_PURCHASE_WAITING)){
				return -1;
			}
		}
		return daoMapper.deleteByIds(ids);
	}
}
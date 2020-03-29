package com.jhy.plateform.service.impl;

import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.PurchaseItemMapper;
import com.jhy.plateform.domain.PurchaseItem;
import com.jhy.plateform.query.PurchaseItemQuery;
import com.jhy.plateform.service.PurchaseItemService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PurchaseItemServiceImpl extends BaseServiceImpl<PurchaseItem, PurchaseItemQuery> implements PurchaseItemService{

	@Autowired
	public void setPurchaseItemMapper(PurchaseItemMapper purchaseItemMapper){
		this.daoMapper = purchaseItemMapper;
	}
}
package com.jhy.plateform.service.impl;

import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.SupplierMapper;
import com.jhy.plateform.domain.Supplier;
import com.jhy.plateform.query.SupplierQuery;
import com.jhy.plateform.service.SupplierService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SupplierServiceImpl extends BaseServiceImpl<Supplier, SupplierQuery> implements SupplierService{

	@Autowired
	public void setSupplierMapper(SupplierMapper supplierMapper){
		this.daoMapper = supplierMapper;
	}
}
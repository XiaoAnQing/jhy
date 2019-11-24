package com.jhy.plateform.service.impl;

import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.ProductTypeMapper;
import com.jhy.plateform.domain.ProductType;
import com.jhy.plateform.query.ProductTypeQuery;
import com.jhy.plateform.service.ProductTypeService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType, ProductTypeQuery> implements ProductTypeService{

	@Autowired
	public void setProductTypeMapper(ProductTypeMapper productTypeMapper){
		this.daoMapper = productTypeMapper;
	}
}
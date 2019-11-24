package com.jhy.plateform.service.impl;

import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.ProductMapper;
import com.jhy.plateform.domain.Product;
import com.jhy.plateform.query.ProductQuery;
import com.jhy.plateform.service.ProductService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductQuery> implements ProductService{

	@Autowired
	public void setProductMapper(ProductMapper productMapper){
		this.daoMapper = productMapper;
	}
}
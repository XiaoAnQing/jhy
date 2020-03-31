package com.jhy.plateform.service.impl;

import com.jhy.plateform.domain.Attach;
import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.ProductMapper;
import com.jhy.plateform.domain.Product;
import com.jhy.plateform.query.ProductQuery;
import com.jhy.plateform.service.ProductService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductQuery> implements ProductService{

	@Autowired
	public void setProductMapper(ProductMapper productMapper){
		this.daoMapper = productMapper;
	}

	@Override
	public boolean bindAttach(Integer productId, List<Attach> attachs) {
		//先解除绑定,然后在绑定
		((ProductMapper)daoMapper).unbind(productId);
		for(Attach attach : attachs){
			attach.setProductId(productId);
			((ProductMapper)daoMapper).bind(attach);
		}
		return true;
	}

	@Override
	public List<Attach> findAttach(Integer productId) {
		return ((ProductMapper)daoMapper).findAttach(productId);
	}
}
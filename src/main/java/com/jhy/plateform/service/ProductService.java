package com.jhy.plateform.service;

import com.jhy.plateform.domain.Attach;
import com.jhy.plateform.domain.Product;
import com.jhy.plateform.query.ProductQuery;
import com.jhy.plateform.service.base.BaseService;

import java.util.List;

public interface ProductService extends BaseService<Product,ProductQuery> {

    boolean bindAttach(Integer productId, List<Attach> attachs);

    List<Attach> findAttach(Integer productId);
}
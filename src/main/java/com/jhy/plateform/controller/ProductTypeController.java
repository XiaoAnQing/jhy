package com.jhy.plateform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.ProductType;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.ProductTypeQuery;
import com.jhy.plateform.service.ProductTypeService;

@Controller
@RequestMapping("/productTypes")
@ControllerAnno(addUI = "/productType/save", detailUI = "/productType/detail", editUI = "/productType/save", listUI = "/productType/list")
public class ProductTypeController extends BaseController<ProductType,ProductTypeQuery>{
	
	@Autowired
	public void setProductTypeService(ProductTypeService productTypeService) {
		this.baseService = productTypeService;
	}

	@Override
	public ProductType beforeSave(ModelMap modelMap, ProductType t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}
}
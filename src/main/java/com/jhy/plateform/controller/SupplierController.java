package com.jhy.plateform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.Supplier;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.SupplierQuery;
import com.jhy.plateform.service.SupplierService;

@Controller
@RequestMapping("/suppliers")
@ControllerAnno(addUI = "/supplier/save", detailUI = "/supplier/detail", editUI = "/supplier/save", listUI = "/supplier/list")
public class SupplierController extends BaseController<Supplier,SupplierQuery>{
	
	@Autowired
	public void setSupplierService(SupplierService supplierService) {
		this.baseService = supplierService;
	}

	@Override
	public Supplier beforeSave(ModelMap modelMap, Supplier t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}
}
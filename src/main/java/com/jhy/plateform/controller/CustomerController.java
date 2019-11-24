package com.jhy.plateform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.Customer;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.CustomerQuery;
import com.jhy.plateform.service.CustomerService;

@Controller
@RequestMapping("/customers")
@ControllerAnno(addUI = "/customer/save", detailUI = "/customer/detail", editUI = "/customer/save", listUI = "/customer/list")
public class CustomerController extends BaseController<Customer,CustomerQuery>{
	
	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.baseService = customerService;
	}

	@Override
	public Customer beforeSave(ModelMap modelMap, Customer t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}
}
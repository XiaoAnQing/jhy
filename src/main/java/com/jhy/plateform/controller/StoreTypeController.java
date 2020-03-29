package com.jhy.plateform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.StoreType;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.StoreTypeQuery;
import com.jhy.plateform.service.StoreTypeService;

@Controller
@RequestMapping("/storeTypes")
@ControllerAnno(addUI = "/storeType/save", detailUI = "/storeType/detail", editUI = "/storeType/save", listUI = "/storeType/list")
public class StoreTypeController extends BaseController<StoreType,StoreTypeQuery>{
	
	@Autowired
	public void setStoreTypeService(StoreTypeService storeTypeService) {
		this.baseService = storeTypeService;
	}

	@Override
	public StoreType beforeSave(ModelMap modelMap, StoreType t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}
}
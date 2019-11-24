package com.jhy.plateform.controller;

import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.DictVal;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.DictValQuery;
import com.jhy.plateform.service.DictValService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/dictVals")
@ControllerAnno(addUI = "/dictVals/save", detailUI = "/dictVals/detail", editUI = "/dictVals/save", listUI = "/dictVals/list")
public class DictValController extends BaseController<DictVal, DictValQuery> {
	
	@Autowired
	public void setDictValService(DictValService dictValService) {
		this.baseService = dictValService;
	}

	@Override
	public DictVal beforeSave(ModelMap modelMap, DictVal t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}
	//TODO 
	//编辑和添加和列表需要隐藏都不需要
}
package com.jhy.plateform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.Line;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.LineQuery;
import com.jhy.plateform.service.LineService;

@Controller
@RequestMapping("/lines")
@ControllerAnno(addUI = "/line/save", detailUI = "/line/detail", editUI = "/line/save", listUI = "/line/list")
public class LineController extends BaseController<Line,LineQuery>{
	
	@Autowired
	public void setLineService(LineService lineService) {
		this.baseService = lineService;
	}

	@Override
	public Line beforeSave(ModelMap modelMap, Line t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}
}
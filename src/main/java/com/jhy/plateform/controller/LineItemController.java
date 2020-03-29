package com.jhy.plateform.controller;

import com.jhy.plateform.service.LineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.LineItem;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.LineItemQuery;
import com.jhy.plateform.service.LineItemService;

@Controller
@RequestMapping("/lineItems")
@ControllerAnno(addUI = "/lineItem/save", detailUI = "/lineItem/detail", editUI = "/lineItem/save", listUI = "/lineItem/list")
public class LineItemController extends BaseController<LineItem,LineItemQuery>{

	@Autowired
	public void setLineItemService(LineItemService lineItemService) {
		this.baseService = lineItemService;
	}

	@Override
	public LineItem beforeSave(ModelMap modelMap, LineItem t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}
}
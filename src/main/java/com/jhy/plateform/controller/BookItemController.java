package com.jhy.plateform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.BookItem;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.BookItemQuery;
import com.jhy.plateform.service.BookItemService;

@Controller
@RequestMapping("/bookItems")
@ControllerAnno(addUI = "/bookItem/save", detailUI = "/bookItem/detail", editUI = "/bookItem/save", listUI = "/bookItem/list")
public class BookItemController extends BaseController<BookItem,BookItemQuery>{
	
	@Autowired
	public void setBookItemService(BookItemService bookItemService) {
		this.baseService = bookItemService;
	}

	@Override
	public BookItem beforeSave(ModelMap modelMap, BookItem t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}
}
package com.jhy.plateform.controller;

import com.github.pagehelper.PageInfo;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.domain.BookItem;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.BookItemQuery;
import com.jhy.plateform.service.BookItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/bookItems")
@ControllerAnno(addUI = "/bookItem/save", detailUI = "/bookItem/detail", editUI = "/bookItem/save", listUI = "/bookItem/list")
public class BookItemController{
	@Autowired
	BookItemService bookItemService;
	 
	/**
	 * 分页查询[返回JSON]
	 * @param bookItemQuery
	 * @param request
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value="", method= RequestMethod.GET, headers="X-Requested-With=XMLHttpRequest")
	public @ResponseBody Map<String,Object> list(BookItemQuery bookItemQuery, HttpServletRequest request) throws KPException{
		Map<String,Object> result;
		result = new HashMap<String,Object>();
		PageInfo<BookItem> results =null;
		results = bookItemService.findBySelective(bookItemQuery);
		result.put("success", true);
		result.put("msg","查询订单明细成功");
		result.put("total", results.getTotal());
		result.put("data", results.getList());
		return result;
	}


	@RequestMapping(value="", method=RequestMethod.GET)
	public String list(HttpServletRequest request,ModelMap modelMap) throws KPException {
		return "/bookItem/list";
	}
}
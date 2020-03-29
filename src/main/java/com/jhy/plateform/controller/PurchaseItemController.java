package com.jhy.plateform.controller;

import com.github.pagehelper.PageInfo;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.domain.PurchaseItem;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.PurchaseItemQuery;
import com.jhy.plateform.service.PurchaseItemService;
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
@RequestMapping("/purchaseItems")
@ControllerAnno(addUI = "/purchaseItem/save", detailUI = "/purchaseItem/detail", editUI = "/purchaseItem/save", listUI = "/purchaseItem/list")
public class PurchaseItemController{
	@Autowired
	PurchaseItemService purchaseItemService;

	/**
	 * 分页查询[返回JSON]
	 * @param purchaseItemQuery
	 * @param request
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value="", method= RequestMethod.GET, headers="X-Requested-With=XMLHttpRequest")
	public @ResponseBody Map<String,Object> list(PurchaseItemQuery purchaseItemQuery, HttpServletRequest request) throws KPException{
		Map<String,Object> result;
		result = new HashMap<String,Object>();
		PageInfo<PurchaseItem> results =null;
		results = purchaseItemService.findBySelective(purchaseItemQuery);
		result.put("success", true);
		result.put("msg","查询采购明细成功");
		result.put("total", results.getTotal());
		result.put("data", results.getList());
		return result;
	}


	@RequestMapping(value="", method=RequestMethod.GET)
	public String list(HttpServletRequest request,ModelMap modelMap) throws KPException {
		return "/purchaseItem/list";
	}
}
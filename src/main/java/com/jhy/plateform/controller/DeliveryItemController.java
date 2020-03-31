package com.jhy.plateform.controller;

import com.github.pagehelper.PageInfo;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.domain.DeliveryItem;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.DeliveryItemQuery;
import com.jhy.plateform.service.DeliveryItemService;
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
@RequestMapping("/deliveryItems")
@ControllerAnno(addUI = "/deliveryItem/save", detailUI = "/deliveryItem/detail", editUI = "/deliveryItem/save", listUI = "/deliveryItem/list")
public class DeliveryItemController{
	@Autowired
	DeliveryItemService deliveryItemService;

	/**
	 * 分页查询[返回JSON]
	 * @param deliveryItemQuery
	 * @param request
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value="", method= RequestMethod.GET, headers="X-Requested-With=XMLHttpRequest")
	public @ResponseBody Map<String,Object> list(DeliveryItemQuery deliveryItemQuery, HttpServletRequest request) throws KPException{
		Map<String,Object> result;
		result = new HashMap<String,Object>();
		PageInfo<DeliveryItem> results =null;
		results = deliveryItemService.findBySelective(deliveryItemQuery);
		result.put("success", true);
		result.put("msg","查询送货单明细成功");
		result.put("total", results.getTotal());
		result.put("data", results.getList());
		return result;
	}


	@RequestMapping(value="", method=RequestMethod.GET)
	public String list(HttpServletRequest request,ModelMap modelMap) throws KPException {
		return "/deliveryItem/list";
	}
}
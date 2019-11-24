package com.jhy.plateform.controller;

import com.github.pagehelper.util.StringUtil;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.Dict;
import com.jhy.plateform.domain.DictVal;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.DictQuery;
import com.jhy.plateform.query.DictValQuery;
import com.jhy.plateform.service.DictService;
import com.jhy.plateform.service.DictValService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/dicts")
@ControllerAnno(addUI = "/dicts/save", detailUI = "/dicts/detail", editUI = "/dicts/save", listUI = "/dicts/list")
public class DictController extends BaseController<Dict, DictQuery> {
	
	@Autowired
	DictValService dictValService;
	
	@Autowired
	public void setDictService(DictService dictService) {
		this.baseService = dictService;
	}

	@Override
	public Dict beforeSave(ModelMap modelMap, Dict t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
		if(!StringUtil.isEmpty(id)){
			DictValQuery dictValQuery = new DictValQuery();
			dictValQuery.setDictId(Integer.parseInt(id));
			dictValQuery.setPagination(false);
			List<DictVal> dictVals =  dictValService.findBySelective(dictValQuery).getList();
			modelMap.addAttribute("dictVals", dictVals);
		}
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}

	@RequestMapping(value="", method= RequestMethod.GET)
	@RequiresPermissions("dict:query")
	@Override
    public String list(HttpServletRequest request, ModelMap modelMap) throws KPException {
		return listUI;
	}
}
package com.jhy.plateform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.Material;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.MaterialQuery;
import com.jhy.plateform.service.MaterialService;
@Controller
@RequestMapping("/materials")
@ControllerAnno(addUI = "/material/save", detailUI = "/material/detail", editUI = "/material/save", listUI = "/material/list")
public class MaterialController extends BaseController<Material,MaterialQuery>{
	@Autowired
	public void setMaterialService(MaterialService materialService) {
		this.baseService = materialService;
	}

	@Override
	public Material beforeSave(ModelMap modelMap, Material t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}
}
package com.jhy.plateform.controller;

import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.FileInfo;
import com.jhy.plateform.domain.User;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.FileInfoQuery;
import com.jhy.plateform.service.FileInfoService;
import com.jhy.plateform.utils.ConstantUtil;
import com.jhy.plateform.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/fileInfos")
@ControllerAnno(addUI = "/fileInfo/save", detailUI = "/fileInfo/detail", editUI = "/fileInfo/save", listUI = "/fileInfo/list")
public class FileInfoController extends BaseController<FileInfo,FileInfoQuery>{
	
	@Autowired
	public void setFileInfoService(FileInfoService fileInfoService) {
		this.baseService = fileInfoService;
	}

	@Override
	public FileInfo beforeSave(ModelMap modelMap, FileInfo t) throws KPException {
		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
		User user = (User) request.getSession().getAttribute(ConstantUtil.SESSION_KEY);
		t.setUserId(user.getId());
		t.setCreateDate(DateUtil.getCurrentDate());
		t.setModifyDate(DateUtil.getCurrentDate());
		t.setMoney(1);
		t.setUserName(user.getName());
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}

	@Override
	public String edit(String id, ModelMap modelMap, FileInfo fileInfo) throws KPException {
		return null;
	}

	@Override
	public String editUI(String id, ModelMap modelMap) throws KPException {
		return null;
	}
}
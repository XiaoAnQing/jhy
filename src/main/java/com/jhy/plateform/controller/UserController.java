package com.jhy.plateform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.User;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.UserQuery;
import com.jhy.plateform.service.UserService;

@Controller
@RequestMapping("/users")
@ControllerAnno(addUI = "/user/save", detailUI = "/user/detail", editUI = "/user/save", listUI = "/user/list")
public class UserController extends BaseController<User,UserQuery>{
	
	@Autowired
	public void setUserService(UserService userService) {
		this.baseService = userService;
	}

	@Override
	public User beforeSave(ModelMap modelMap, User t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}
}
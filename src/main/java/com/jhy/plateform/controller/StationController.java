package com.jhy.plateform.controller;

import com.jhy.plateform.domain.User;
import com.jhy.plateform.query.UserQuery;
import com.jhy.plateform.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.Station;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.StationQuery;
import com.jhy.plateform.service.StationService;

import java.util.List;

@Controller
@RequestMapping("/stations")
@ControllerAnno(addUI = "/station/save", detailUI = "/station/detail", editUI = "/station/save", listUI = "/station/list")
public class StationController extends BaseController<Station,StationQuery>{

	@Autowired
	private UserService userService;
	
	@Autowired
	public void setStationService(StationService stationService) {
		this.baseService = stationService;
	}

	@Override
	public Station beforeSave(ModelMap modelMap, Station t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
		//加载所有的员工信息
		UserQuery userQuery = new UserQuery();
		userQuery.setPagination(false);
		List<User> users = userService.findBySelective(userQuery).getList();
		modelMap.put("users",users);
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}
}
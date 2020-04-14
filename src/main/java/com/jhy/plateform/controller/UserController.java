package com.jhy.plateform.controller;

import com.jhy.plateform.domain.Station;
import com.jhy.plateform.dto.StationTaskCount;
import com.jhy.plateform.query.StationQuery;
import com.jhy.plateform.service.StationService;
import com.jhy.plateform.utils.ConstantUtil;
import com.jhy.plateform.utils.JsonModel;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/users")
@ControllerAnno(addUI = "/user/save", detailUI = "/user/detail", editUI = "/user/save", listUI = "/user/list")
public class UserController extends BaseController<User,UserQuery>{

	@Autowired
	StationService stationService;
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

	@RequestMapping(value="stations",method= RequestMethod.GET)
    @ResponseBody
	public JsonModel findStation(){
		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
		JsonModel jsonModel = new JsonModel();
		StationQuery stationQuery = new StationQuery();
		User user = (User) request.getSession().getAttribute(ConstantUtil.SESSION_KEY);
		stationQuery.setManagerId(user.getId());
		stationQuery.setPagination(false);
		List<StationTaskCount> stationTaskCounts = stationService.findTaskCountBySelective(stationQuery).getList();
		jsonModel.setData(stationTaskCounts);
		jsonModel.setSuccess(true);
		jsonModel.setMsg("查找工序成功");
		return jsonModel;
	}
}
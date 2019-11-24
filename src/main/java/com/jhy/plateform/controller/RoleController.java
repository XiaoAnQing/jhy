package com.jhy.plateform.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.Role;
import com.jhy.plateform.exception.ExceptionKind;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.RoleQuery;
import com.jhy.plateform.service.MenuService;
import com.jhy.plateform.service.RoleService;
import com.jhy.plateform.utils.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/roles")
@ControllerAnno(addUI = "/role/save", detailUI = "/role/detail", editUI = "/role/save", listUI = "/role/list")
public class RoleController extends BaseController<Role, RoleQuery> {
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.baseService = roleService;
	}
	
	@Autowired
	MenuService menuService;
	
	@Override
	public Map<String, Object> list(RoleQuery e, HttpServletRequest request) throws KPException {
		return super.list(e, request);
	}
	

	/**
	 * 
	 * @Title: menuService 
	 * @Description:    获取指定角色的权限信息
	 * @param @param roleId
	 * @param @return     
	 * @return JsonModel     
	 * @throws
	 */
    @RequestMapping(value="/{roleId}/menus",method = RequestMethod.GET)
	public @ResponseBody
	JsonModel roleMenuIds(@PathVariable String roleId){
		JsonModel jsonModel = new JsonModel();
		List<Integer> result = menuService.findByRole(Integer.parseInt(roleId));
		jsonModel.setData(result);
		jsonModel.setSuccess(true);
		return jsonModel;
	}
	
	
	/**
	 * 授权
	 * @param roleId
	 * @param menuIds
	 * @return
	 * @throws KPException
	 */
    @RequestMapping(value="/{roleId}/menus",method = RequestMethod.PUT)
	public @ResponseBody JsonModel grant(@PathVariable Integer roleId,Integer [] menuIds) throws KPException{
    	if(roleId==null){
    		throw new KPException(ExceptionKind.PARAM_E);
    	}
		JsonModel jsonModel = new JsonModel();
		menuService.updateAuth(roleId,menuIds);
		jsonModel.setMsg("成功赋值权限");
		jsonModel.setSuccess(true);
		return jsonModel;
	}
	

	@Override
	public Role beforeSave(ModelMap modelMap, Role t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}
}
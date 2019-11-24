package com.jhy.plateform.service;

import com.jhy.plateform.domain.Menu;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.MenuQuery;
import com.jhy.plateform.service.base.BaseService;

import java.util.List;
import java.util.Map;

public interface MenuService extends BaseService<Menu, MenuQuery> {

	List<Menu> findByParent(Integer parentId);
	
	List<Integer> findByRole(Integer roleId);
	
	void updateAuth(Integer roleId, Integer[] privIds) throws KPException;
	
    int clearGrant(Map<String, Integer> param);

	List<Menu> findByRoleIds(Integer[] roleId);

	List<Menu> findLevelMenu(); 
}
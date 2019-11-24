package com.jhy.plateform.service;

import com.jhy.plateform.domain.Role;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.RoleQuery;
import com.jhy.plateform.service.base.BaseService;

public interface RoleService extends BaseService<Role, RoleQuery> {
	Integer [] findIdsByUserId(Integer userId);
	
	void bind(Integer[] roleIds, Integer usreId) throws KPException;
	
    void unbind(Integer userId);
}
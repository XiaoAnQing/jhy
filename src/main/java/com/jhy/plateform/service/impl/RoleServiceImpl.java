package com.jhy.plateform.service.impl;

import com.jhy.plateform.dao.RoleMapper;
import com.jhy.plateform.domain.Role;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.RoleQuery;
import com.jhy.plateform.service.RoleService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleQuery> implements RoleService{

	@Autowired
	public void setRoleMapper(RoleMapper roleMapper){
		this.daoMapper = roleMapper;
	}

	@Override
	public Integer[] findIdsByUserId(Integer userId) {
		return ((RoleMapper)this.daoMapper).findIdsByUserId(userId);
	}

	@Override
	public void bind(Integer[] roleIds, Integer usreId) throws KPException {
		((RoleMapper)this.daoMapper).bind(roleIds,usreId);
	}

	@Override
	public void unbind(Integer userId) {
		((RoleMapper)this.daoMapper).unbind(userId);
	}
}
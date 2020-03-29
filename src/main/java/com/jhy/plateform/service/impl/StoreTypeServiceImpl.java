package com.jhy.plateform.service.impl;

import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.StoreTypeMapper;
import com.jhy.plateform.domain.StoreType;
import com.jhy.plateform.query.StoreTypeQuery;
import com.jhy.plateform.service.StoreTypeService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class StoreTypeServiceImpl extends BaseServiceImpl<StoreType, StoreTypeQuery> implements StoreTypeService{

	@Autowired
	public void setStoreTypeMapper(StoreTypeMapper storeTypeMapper){
		this.daoMapper = storeTypeMapper;
	}
}
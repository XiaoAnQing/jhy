package com.jhy.plateform.service.impl;

import com.jhy.plateform.domain.Material;
import com.jhy.plateform.exception.ExceptionKind;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.service.MaterialService;
import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.StoreMapper;
import com.jhy.plateform.domain.Store;
import com.jhy.plateform.query.StoreQuery;
import com.jhy.plateform.service.StoreService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class StoreServiceImpl extends BaseServiceImpl<Store, StoreQuery> implements StoreService{

	@Autowired
	MaterialService materialService;

	@Autowired
	StoreMapper storeMapper;

	@Autowired
	public void setStoreMapper(StoreMapper storeMapper){
		this.daoMapper = storeMapper;
	}

	@Override
	public boolean unbind(Integer storeId) {
		return ((StoreMapper)daoMapper).unbind(storeId)==1;
	}

	@Override
	public boolean bind(Integer storeId, Integer materialId) {
		Material material = materialService.findById(materialId+"");
		if(material==null){
			throw new KPException(ExceptionKind.PARAM_E);
		}
		Store store = new Store();
		store.setId(storeId);
		store.setMaterialId(materialId);
		store.setMaterialName(material.getName());
		store.setMaterialCount(0);

		return updateById(store)==1;
	}

	@Override
	public List<Store> findByMaterialId(Integer materialId) {
		return storeMapper.findByMaterialId(materialId);
	}
}
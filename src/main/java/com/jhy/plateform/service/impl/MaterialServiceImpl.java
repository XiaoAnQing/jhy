package com.jhy.plateform.service.impl;

import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.MaterialMapper;
import com.jhy.plateform.domain.Material;
import com.jhy.plateform.query.MaterialQuery;
import com.jhy.plateform.service.MaterialService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MaterialServiceImpl extends BaseServiceImpl<Material, MaterialQuery> implements MaterialService{

	@Autowired
	public void setMaterialMapper(MaterialMapper materialMapper){
		this.daoMapper = materialMapper;
	}
}
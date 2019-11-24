package com.jhy.plateform.service.impl;


import com.jhy.plateform.dao.MaterialTypeMapper;
import com.jhy.plateform.domain.MaterialType;
import com.jhy.plateform.query.MaterialTypeQuery;
import com.jhy.plateform.service.MaterialTypeService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialTypeServiceImpl extends BaseServiceImpl<MaterialType, MaterialTypeQuery> implements MaterialTypeService {

    @Autowired
    public void setDao(MaterialTypeMapper materialTypeMapper){
        this.daoMapper = materialTypeMapper;
    }
}

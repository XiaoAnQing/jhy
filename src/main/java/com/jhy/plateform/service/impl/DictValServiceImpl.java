package com.jhy.plateform.service.impl;

import com.jhy.plateform.dao.DictValMapper;
import com.jhy.plateform.domain.DictVal;
import com.jhy.plateform.query.DictValQuery;
import com.jhy.plateform.service.DictValService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DictValServiceImpl extends BaseServiceImpl<DictVal, DictValQuery> implements DictValService {

	@Autowired
	public void setDictValMapper(DictValMapper dictValMapper){
		this.daoMapper = dictValMapper;
	}	
	
	@Override
	public void clearByDict(Integer dictId){
		((DictValMapper)this.daoMapper).clearByDict(dictId);
	}

	@Override
	public int batchInsert(List<DictVal> dictVals) {
		return ((DictValMapper)this.daoMapper).batchInsert(dictVals);
	}

}
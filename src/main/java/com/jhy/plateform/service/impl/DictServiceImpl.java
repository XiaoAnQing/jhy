package com.jhy.plateform.service.impl;

import com.jhy.plateform.dao.DictMapper;
import com.jhy.plateform.domain.Dict;
import com.jhy.plateform.domain.DictVal;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.DictQuery;
import com.jhy.plateform.service.DictService;
import com.jhy.plateform.service.DictValService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl extends BaseServiceImpl<Dict, DictQuery> implements DictService {

	
	@Autowired
	DictValService dictValService;
	
	@Autowired
	public void setDictMapper(DictMapper dictMapper){
		this.daoMapper = dictMapper;
	}
	
	@Override
	public int add(Dict t) throws KPException {
		int effectCount =  super.add(t);
		List<DictVal> dictVals = t.getDictVals();
		for(DictVal dictVal : dictVals){
			dictVal.setDict(t);
		}
		//批量添加
		int count = dictValService.batchInsert(dictVals);
		return effectCount;
	}
	
	@Override
	public int updateById(Dict t) throws KPException {
		dictValService.clearByDict(t.getId());
		
		List<DictVal> dictVals = t.getDictVals();
		if(dictVals!=null){
			for(DictVal dictVal : dictVals){
				dictVal.setDict(t);
			}
		}
		//批量添加
		int count = dictValService.batchInsert(dictVals);
		return super.updateById(t);
	}

	@Override
	public List<Dict> selectDict() {
		return ((DictMapper)this.daoMapper).selectDict();
	}
}
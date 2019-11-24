package com.jhy.plateform.service;

import com.jhy.plateform.domain.DictVal;
import com.jhy.plateform.query.DictValQuery;
import com.jhy.plateform.service.base.BaseService;

import java.util.List;
public interface DictValService extends BaseService<DictVal, DictValQuery> {
	void clearByDict(Integer dictId);

	int batchInsert(List<DictVal> dictVals);
}
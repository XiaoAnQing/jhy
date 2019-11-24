package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.DictVal;
import com.jhy.plateform.query.DictValQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictValMapper extends BaseDao<DictVal, DictValQuery> {
	void clearByDict(Integer dictId);
	
	int batchInsert(List<DictVal> dictVals);
}
package com.jhy.plateform.service.impl;

import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.LineMapper;
import com.jhy.plateform.domain.Line;
import com.jhy.plateform.query.LineQuery;
import com.jhy.plateform.service.LineService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class LineServiceImpl extends BaseServiceImpl<Line, LineQuery> implements LineService{

	@Autowired
	public void setLineMapper(LineMapper lineMapper){
		this.daoMapper = lineMapper;
	}

	@Override
	public List<Line> findSummary() {
		return ((LineMapper)this.daoMapper).findSummary();
	}
}
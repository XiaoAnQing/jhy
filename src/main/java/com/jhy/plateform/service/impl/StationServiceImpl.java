package com.jhy.plateform.service.impl;

import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.StationMapper;
import com.jhy.plateform.domain.Station;
import com.jhy.plateform.query.StationQuery;
import com.jhy.plateform.service.StationService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class StationServiceImpl extends BaseServiceImpl<Station, StationQuery> implements StationService{

	@Autowired
	public void setStationMapper(StationMapper stationMapper){
		this.daoMapper = stationMapper;
	}
}
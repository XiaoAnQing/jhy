package com.jhy.plateform.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhy.plateform.dto.StationTaskCount;
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

	@Override
	public PageInfo<StationTaskCount> findTaskCountBySelective(StationQuery stationQuery) {
		if(stationQuery.isPagination()){
			PageHelper.startPage(stationQuery.getPageStart(),stationQuery.getPageSize());
			Page<StationTaskCount> list = (Page<StationTaskCount>) ((StationMapper)this.daoMapper).findTaskCountBySelective(stationQuery);
			return list.toPageInfo();
		}
		return new PageInfo<StationTaskCount>(((StationMapper)this.daoMapper).findTaskCountBySelective(stationQuery));
	}
}
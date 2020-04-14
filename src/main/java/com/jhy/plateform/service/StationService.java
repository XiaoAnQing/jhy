package com.jhy.plateform.service;

import com.github.pagehelper.PageInfo;
import com.jhy.plateform.domain.Station;
import com.jhy.plateform.dto.StationTaskCount;
import com.jhy.plateform.query.StationQuery;
import com.jhy.plateform.service.base.BaseService;
public interface StationService extends BaseService<Station,StationQuery> {

    PageInfo<StationTaskCount> findTaskCountBySelective(StationQuery stationQuery);

}
package com.jhy.plateform.dao;
import com.github.pagehelper.Page;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.Station;
import com.jhy.plateform.dto.StationTaskCount;
import com.jhy.plateform.query.StationQuery;
import com.jhy.plateform.domain.Station;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StationMapper extends BaseDao<Station,StationQuery> {
    List<StationTaskCount> findTaskCountBySelective(StationQuery stationQuery);
}


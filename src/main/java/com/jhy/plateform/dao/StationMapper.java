package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.Station;
import com.jhy.plateform.query.StationQuery;
import com.jhy.plateform.domain.Station;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface StationMapper extends BaseDao<Station,StationQuery> {
}
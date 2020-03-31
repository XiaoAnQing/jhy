package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.Line;
import com.jhy.plateform.query.LineQuery;
import com.jhy.plateform.domain.Line;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LineMapper extends BaseDao<Line,LineQuery> {
    List<Line> findSummary();
}
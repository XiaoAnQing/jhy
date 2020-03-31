package com.jhy.plateform.service;

import com.jhy.plateform.domain.Line;
import com.jhy.plateform.query.LineQuery;
import com.jhy.plateform.service.base.BaseService;

import java.util.List;

public interface LineService extends BaseService<Line,LineQuery> {
    List<Line> findSummary();
}
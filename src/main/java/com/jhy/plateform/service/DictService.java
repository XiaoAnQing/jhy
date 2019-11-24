package com.jhy.plateform.service;

import com.jhy.plateform.domain.Dict;
import com.jhy.plateform.query.DictQuery;
import com.jhy.plateform.service.base.BaseService;

import java.util.List;

public interface DictService extends BaseService<Dict, DictQuery> {
    List<Dict> selectDict();
}
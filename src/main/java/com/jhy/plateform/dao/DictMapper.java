package com.jhy.plateform.dao;

import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.Dict;
import com.jhy.plateform.query.DictQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DictMapper extends BaseDao<Dict, DictQuery> {
    List<Dict> selectDict();
}
package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.LineItem;
import com.jhy.plateform.query.LineItemQuery;
import com.jhy.plateform.domain.LineItem;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface LineItemMapper extends BaseDao<LineItem,LineItemQuery> {
}
package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.BookItem;
import com.jhy.plateform.query.BookItemQuery;
import com.jhy.plateform.domain.BookItem;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface BookItemMapper extends BaseDao<BookItem,BookItemQuery> {
}
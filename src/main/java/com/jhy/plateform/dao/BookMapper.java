package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.Book;
import com.jhy.plateform.domain.BookCount;
import com.jhy.plateform.query.BookQuery;
import com.jhy.plateform.domain.Book;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface BookMapper extends BaseDao<Book,BookQuery> {

    int count(BookCount bookCount);
}
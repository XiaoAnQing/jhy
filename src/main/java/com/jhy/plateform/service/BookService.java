package com.jhy.plateform.service;

import com.jhy.plateform.domain.Book;
import com.jhy.plateform.domain.BookCount;
import com.jhy.plateform.query.BookQuery;
import com.jhy.plateform.service.base.BaseService;
public interface BookService extends BaseService<Book,BookQuery> {

    int count(BookCount bookCount);
}
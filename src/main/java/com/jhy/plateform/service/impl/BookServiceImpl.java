package com.jhy.plateform.service.impl;

import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.BookMapper;
import com.jhy.plateform.domain.Book;
import com.jhy.plateform.query.BookQuery;
import com.jhy.plateform.service.BookService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BookServiceImpl extends BaseServiceImpl<Book, BookQuery> implements BookService{

	@Autowired
	public void setBookMapper(BookMapper bookMapper){
		this.daoMapper = bookMapper;
	}
}
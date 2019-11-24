package com.jhy.plateform.service.impl;

import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.BookItemMapper;
import com.jhy.plateform.domain.BookItem;
import com.jhy.plateform.query.BookItemQuery;
import com.jhy.plateform.service.BookItemService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BookItemServiceImpl extends BaseServiceImpl<BookItem, BookItemQuery> implements BookItemService{

	@Autowired
	public void setBookItemMapper(BookItemMapper bookItemMapper){
		this.daoMapper = bookItemMapper;
	}
}
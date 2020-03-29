package com.jhy.plateform.service.impl;

import com.jhy.plateform.dao.BookMapper;
import com.jhy.plateform.domain.Book;
import com.jhy.plateform.domain.BookItem;
import com.jhy.plateform.domain.Product;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.BookQuery;
import com.jhy.plateform.service.BookItemService;
import com.jhy.plateform.service.BookService;
import com.jhy.plateform.service.ProductService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import com.jhy.plateform.utils.ConstantUtil;
import com.jhy.plateform.utils.DateUtil;
import com.jhy.plateform.utils.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl extends BaseServiceImpl<Book, BookQuery> implements BookService{

	@Autowired
	public void setBookMapper(BookMapper bookMapper){
		this.daoMapper = bookMapper;
	}

	@Autowired
	ProductService productService;
	@Autowired
	BookItemService bookItemService;

	@Override
	public int add(Book book) {
		//准备数据
		book.setCreateDate(DateUtil.getCurrentDate());
		//状态
		book.setStatus(StatusUtil.STATUS_BOOK_WAITING);

		BigDecimal price = BigDecimal.ZERO;

		List<BookItem> bookItems = book.getBookItems();

		for(BookItem bookItem : bookItems) {
			bookItem.setBookNum(book.getNum());
			Product product = productService.findById(bookItem.getProductId()+"");
			bookItem.setName(product.getName());
			bookItem.setImg(product.getDetailImg());
			bookItem.setPrice(product.getPrice());

			bookItem.setTotalPrice(product.getPrice().multiply(new BigDecimal(bookItem.getTotalCount())));
			price = price.add(bookItem.getTotalPrice());
		}

		book.setPrice(price);
		int result =  daoMapper.add(book);


		for(BookItem bookItem : bookItems) {
			//保存
			bookItemService.add(bookItem);
		}
		return result;
	}



	@Override
	public int deleteById(String id) throws KPException {
		//判断状态
		Book book = daoMapper.findById(id);
		if(book==null || !book.getStatus().equals(StatusUtil.STATUS_BOOK_WAITING)){
			return -1;
		}
		return daoMapper.deleteById(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public int deleteByIds(String[] ids) {
		for(String id : ids){
			Book book = daoMapper.findById(id);
			if(book==null || !book.getStatus().equals(StatusUtil.STATUS_BOOK_WAITING)){
				return -1;
			}
		}
		return daoMapper.deleteByIds(ids);
	}

}
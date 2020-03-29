package com.jhy.plateform.query;
import com.jhy.plateform.query.base.BaseQuery;
public class BookItemQuery extends BaseQuery{

	private String bookNum;

	public String getBookNum() {
		return bookNum;
	}

	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}

	@Override
	public String getOrder() {
		return null;
	}

	@Override
	public void addQuery(String condition, Object... param) {
	}
}

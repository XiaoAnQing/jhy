package com.jhy.plateform.query.base;
public interface IQuery {
	public String getOrder();
	 
	public void addQuery(String condition, Object... param);
}

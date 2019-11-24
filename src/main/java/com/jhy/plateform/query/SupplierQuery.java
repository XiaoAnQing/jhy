package com.jhy.plateform.query;
import com.jhy.plateform.query.base.BaseQuery;
public class SupplierQuery extends BaseQuery{

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getOrder() {
		return null;
	}

	@Override
	public void addQuery(String condition, Object... param) {
	}
}

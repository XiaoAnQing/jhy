package com.jhy.plateform.query;

import com.jhy.plateform.query.base.BaseQuery;

public class MenuQuery extends BaseQuery {
	
	private Integer parentId;
	

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Override
	public String getOrder() {
		return null;
	}

	@Override
	public void addQuery(String condition, Object... param) {
	}
}

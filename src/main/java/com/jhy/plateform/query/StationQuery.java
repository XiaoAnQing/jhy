package com.jhy.plateform.query;
import com.jhy.plateform.query.base.BaseQuery;
public class StationQuery extends BaseQuery{

	private Integer managerId;

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	@Override
	public String getOrder() {
		return null;
	}

	@Override
	public void addQuery(String condition, Object... param) {
	}
}

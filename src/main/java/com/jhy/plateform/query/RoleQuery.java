package com.jhy.plateform.query;

import com.jhy.plateform.query.base.BaseQuery;

public class RoleQuery extends BaseQuery {
	
	private Integer agentId;

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	@Override
	public String getOrder() {
		return null;
	}

	@Override
	public void addQuery(String condition, Object... param) {
	}
}

package com.jhy.plateform.query;
import com.jhy.plateform.query.base.BaseQuery;
public class PurchaseQuery extends BaseQuery{

	private Byte status;

	@Override
	public String getOrder() {
		return null;
	}

	@Override
	public void addQuery(String condition, Object... param) {
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
}

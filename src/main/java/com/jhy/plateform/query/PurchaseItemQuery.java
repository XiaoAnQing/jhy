package com.jhy.plateform.query;
import com.jhy.plateform.query.base.BaseQuery;
public class PurchaseItemQuery extends BaseQuery{

	private String purchaseNum;
	private Integer materialId;

	public String getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(String purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	@Override
	public String getOrder() {
		return null;
	}

	@Override
	public void addQuery(String condition, Object... param) {
	}
}

package com.jhy.plateform.query;
import com.jhy.plateform.query.base.BaseQuery;
public class MaterialQuery extends BaseQuery{

	private Integer materialTypeId;

	private String name;

	private Integer supplierId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMaterialTypeId() {
		return materialTypeId;
	}

	public void setMaterialTypeId(Integer materialTypeId) {
		this.materialTypeId = materialTypeId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Override
	public String getOrder() {
		return null;
	}

	@Override
	public void addQuery(String condition, Object... param) {
	}
}

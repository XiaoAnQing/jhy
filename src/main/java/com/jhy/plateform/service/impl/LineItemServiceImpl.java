package com.jhy.plateform.service.impl;

import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.LineItemMapper;
import com.jhy.plateform.domain.LineItem;
import com.jhy.plateform.query.LineItemQuery;
import com.jhy.plateform.service.LineItemService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class LineItemServiceImpl extends BaseServiceImpl<LineItem, LineItemQuery> implements LineItemService{

	@Autowired
	public void setLineItemMapper(LineItemMapper lineItemMapper){
		this.daoMapper = lineItemMapper;
	}
}
package com.jhy.plateform.service.impl;

import com.jhy.plateform.dao.LineItemMapper;
import com.jhy.plateform.domain.LineItem;
import com.jhy.plateform.domain.Station;
import com.jhy.plateform.exception.ExceptionKind;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.LineItemQuery;
import com.jhy.plateform.service.LineItemService;
import com.jhy.plateform.service.StationService;
import com.jhy.plateform.service.UserService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineItemServiceImpl extends BaseServiceImpl<LineItem, LineItemQuery> implements LineItemService{

	@Autowired
	UserService userService;

	@Autowired
	StationService stationService;
	@Autowired
	public void setLineItemMapper(LineItemMapper lineItemMapper){
		this.daoMapper = lineItemMapper;
	}

	@Override
	public int add(LineItem lineItem) throws KPException {
		Station station = stationService.findById(lineItem.getStationId()+"");
		if(station == null){
			throw new KPException(ExceptionKind.PARAM_E);
		}
		lineItem.setName(station.getName());
		lineItem.setIcon(station.getIcon());
		lineItem.setPosition(station.getPosition());
		lineItem.setMangerId(station.getManagerId());
		return super.add(lineItem);
	}
}
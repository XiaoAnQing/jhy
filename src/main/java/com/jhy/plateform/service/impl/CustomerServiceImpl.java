package com.jhy.plateform.service.impl;

import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.CustomerMapper;
import com.jhy.plateform.domain.Customer;
import com.jhy.plateform.query.CustomerQuery;
import com.jhy.plateform.service.CustomerService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, CustomerQuery> implements CustomerService{

	@Autowired
	public void setCustomerMapper(CustomerMapper customerMapper){
		this.daoMapper = customerMapper;
	}
}
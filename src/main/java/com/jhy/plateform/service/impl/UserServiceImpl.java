package com.jhy.plateform.service.impl;

import com.jhy.plateform.utils.MD5Util;
import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.UserMapper;
import com.jhy.plateform.domain.User;
import com.jhy.plateform.query.UserQuery;
import com.jhy.plateform.service.UserService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserQuery> implements UserService{

	@Autowired
	public void setUserMapper(UserMapper userMapper){
		this.daoMapper = userMapper;
	}

    @Override
    public User login(String loginName, String password) {
        return ((UserMapper)daoMapper).login(loginName, MD5Util.digest(password));
    }

    @Override
    public User findByLoginName(String loginName) {
        return ((UserMapper)daoMapper).findByLoginName(loginName);
    }
}
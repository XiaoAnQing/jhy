package com.jhy.plateform.service;

import com.jhy.plateform.domain.User;
import com.jhy.plateform.query.UserQuery;
import com.jhy.plateform.service.base.BaseService;
public interface UserService extends BaseService<User,UserQuery> {

    User login(String loginName, String password);

    User findByLoginName(String loginName);
}
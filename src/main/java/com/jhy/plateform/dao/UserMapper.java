package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.User;
import com.jhy.plateform.query.UserQuery;
import com.jhy.plateform.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseDao<User,UserQuery> {
    User login(@Param("loginName") String loginName, @Param("password")String password);

    User findByLoginName(String loginName);
}
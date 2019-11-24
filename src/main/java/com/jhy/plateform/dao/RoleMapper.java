package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.Role;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.RoleQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RoleMapper extends BaseDao<Role, RoleQuery> {
	
	Integer [] findIdsByUserId(Integer userId);
	
	void bind(@Param("roleIds") Integer[] roleIds, @Param("userId") Integer usreId) throws KPException;
	
    void unbind(Integer userId);
}
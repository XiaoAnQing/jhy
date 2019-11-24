package com.jhy.plateform.dao;
import java.util.List;
import java.util.Map;

import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.Menu;
import com.jhy.plateform.query.MenuQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface MenuMapper extends BaseDao<Menu, MenuQuery> {
	List<Menu> findByParent(@Param("parentId") Integer parentId);
	
	List<Integer> findByRole(Integer roleId);
	
    int clearGrant(Map<String, Integer> param);
	
    int grant(Map<String, Integer> param);

	List<Menu> findByRoleIds(Integer[] roleId);
	
	List<Menu> findLevelMenu(); 
}
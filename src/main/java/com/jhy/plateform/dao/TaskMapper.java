package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.Task;
import com.jhy.plateform.query.TaskQuery;
import com.jhy.plateform.domain.Task;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface TaskMapper extends BaseDao<Task,TaskQuery> {
}
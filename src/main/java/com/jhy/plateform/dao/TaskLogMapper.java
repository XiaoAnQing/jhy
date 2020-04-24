package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.TaskLog;
import com.jhy.plateform.query.TaskLogQuery;
import com.jhy.plateform.domain.TaskLog;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface TaskLogMapper extends BaseDao<TaskLog,TaskLogQuery> {
}
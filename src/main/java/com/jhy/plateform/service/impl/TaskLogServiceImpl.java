package com.jhy.plateform.service.impl;

import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.TaskLogMapper;
import com.jhy.plateform.domain.TaskLog;
import com.jhy.plateform.query.TaskLogQuery;
import com.jhy.plateform.service.TaskLogService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TaskLogServiceImpl extends BaseServiceImpl<TaskLog, TaskLogQuery> implements TaskLogService{

	@Autowired
	public void setTaskLogMapper(TaskLogMapper task_logMapper){
		this.daoMapper = task_logMapper;
	}
}
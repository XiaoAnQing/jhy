package com.jhy.plateform.service.impl;

import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.TaskMapper;
import com.jhy.plateform.domain.Task;
import com.jhy.plateform.query.TaskQuery;
import com.jhy.plateform.service.TaskService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TaskServiceImpl extends BaseServiceImpl<Task, TaskQuery> implements TaskService{

	@Autowired
	public void setTaskMapper(TaskMapper taskMapper){
		this.daoMapper = taskMapper;
	}
}
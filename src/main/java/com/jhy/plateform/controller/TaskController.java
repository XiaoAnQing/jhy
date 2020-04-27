package com.jhy.plateform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.Task;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.TaskQuery;
import com.jhy.plateform.service.TaskService;

@Controller
@RequestMapping("/tasks")
@ControllerAnno(addUI = "/task/save", detailUI = "/task/detail", editUI = "/task/save", listUI = "/task/list")
public class TaskController extends BaseController<Task,TaskQuery>{
	
	@Autowired
	public void setTaskService(TaskService taskService) {
		this.baseService = taskService;
	}

	@Override
	public Task beforeSave(ModelMap modelMap, Task t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}
}
package com.jhy.plateform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.TaskLog;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.TaskLogQuery;
import com.jhy.plateform.service.TaskLogService;

@Controller
@RequestMapping("/taskLogs")
@ControllerAnno(addUI = "/taskLog/save", detailUI = "/taskLog/detail", editUI = "/taskLog/save", listUI = "/taskLog/list")
public class TaskLogController extends BaseController<TaskLog,TaskLogQuery>{
	
	@Autowired
	public void setTaskLogService(TaskLogService taskLogService) {
		this.baseService = taskLogService;
	}

	@Override
	public TaskLog beforeSave(ModelMap modelMap, TaskLog t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}
}
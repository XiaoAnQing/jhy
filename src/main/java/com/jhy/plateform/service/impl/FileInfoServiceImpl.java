package com.jhy.plateform.service.impl;

import org.springframework.stereotype.Service;
import com.jhy.plateform.dao.FileInfoMapper;
import com.jhy.plateform.domain.FileInfo;
import com.jhy.plateform.query.FileInfoQuery;
import com.jhy.plateform.service.FileInfoService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class FileInfoServiceImpl extends BaseServiceImpl<FileInfo, FileInfoQuery> implements FileInfoService{

	@Autowired
	public void setFileInfoMapper(FileInfoMapper fileInfoMapper){
		this.daoMapper = fileInfoMapper;
	}
}
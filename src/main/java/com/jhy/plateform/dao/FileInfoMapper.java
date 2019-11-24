package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.FileInfo;
import com.jhy.plateform.query.FileInfoQuery;
import com.jhy.plateform.domain.FileInfo;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface FileInfoMapper extends BaseDao<FileInfo,FileInfoQuery> {
}
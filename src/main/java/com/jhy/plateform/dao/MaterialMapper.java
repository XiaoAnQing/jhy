package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.Material;
import com.jhy.plateform.query.MaterialQuery;
import com.jhy.plateform.domain.Material;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface MaterialMapper extends BaseDao<Material,MaterialQuery> {
}
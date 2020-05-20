package com.jhy.plateform.dao;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.domain.Store;
import com.jhy.plateform.query.StoreQuery;
import com.jhy.plateform.domain.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper extends BaseDao<Store,StoreQuery> {
    int unbind(Integer storeId);

    List<Store> findByMaterialId(Integer materialId);
}
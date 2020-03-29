package com.jhy.plateform.service;

import com.jhy.plateform.domain.Store;
import com.jhy.plateform.query.StoreQuery;
import com.jhy.plateform.service.base.BaseService;
public interface StoreService extends BaseService<Store,StoreQuery> {
    boolean unbind(Integer storeId);

    boolean bind(Integer storeId, Integer materialId);
}
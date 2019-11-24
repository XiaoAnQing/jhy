package com.jhy.plateform.service.base;

import com.github.pagehelper.PageInfo;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.base.BaseQuery;

import java.util.List;
import java.util.Map;
/**
  * 
  * @ClassName: BaseService
  * @Company: 麦子科技
  * @Description: 泛型Service接口
  * @author 技术部-刘强峰
  * @date 2016年4月2日 下午5:21:38
  *
  * @param <T>
 */
public interface BaseService<T,E extends BaseQuery> {
    
    int check(Map<String, Object> param);
    
    int count(Map<String, Object> param);
    
    //根据id查询分页
    T findById(String id);
    
    List<T> findAll();
    
    PageInfo<T> findBySelective(E e);
    
    List<T> findByIds(String... ids);
    

    int add(T t) throws KPException;

    int updateById(T t) throws KPException;

    //删除
    int deleteById(String id) throws KPException;

    //批量删除
	int deleteByIds(String... id);
}
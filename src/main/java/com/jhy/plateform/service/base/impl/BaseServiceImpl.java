package com.jhy.plateform.service.base.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhy.plateform.anno.ClassInfoAnno;
import com.jhy.plateform.dao.base.BaseDao;
import com.jhy.plateform.exception.ExceptionKind;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.base.BaseQuery;
import com.jhy.plateform.service.base.BaseService;
import org.apache.ibatis.type.JdbcType;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * BaseService实现类
 * @author Administrator
 *
 * @param <T>
 * @param <E>
 */
@Transactional
public class BaseServiceImpl<T,E extends BaseQuery> implements BaseService<T,E> {
    protected BaseDao<T,E> daoMapper;
    
    protected Class<T> clazz;
    
    protected String msg;    //模块
    public BaseServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class) pt.getActualTypeArguments()[0];
	      if(clazz.isAnnotationPresent(ClassInfoAnno.class)){
	      	  this.msg=((ClassInfoAnno) clazz.getAnnotation(ClassInfoAnno.class)).msg();
	      }
    }
    
    /**
     * 
     * Title: check
     * Description:    根据条件统计数量[一般用于ajax校验]
     * @param param
     * @return  
     */
	@Override
	@Transactional(readOnly = true)
	public int check(Map<String, Object> param) {
		return daoMapper.check(param);
	}
	
	/**
	 * 
	 * Title: count
	 * Description:    根据条件统计数量[一般用于Service实现类中统计]
	 * @param param
	 * @return  
	 */
	@Override
	@Transactional(readOnly = true)
	public int count(Map<String, Object> param) {
		return daoMapper.count(param);
	}

	/**
	 * 根据Id查询
	 * @param id
	 * @return
	 */
    @Override
	@Transactional(readOnly = true)
    public T findById(String id) {
    	return (T) daoMapper.findById(id);
    }
    
    /**
     * 根据Id集合查询
     */
    @Override
	@Transactional(readOnly = true)
	public List<T> findByIds(String... ids) {
		return daoMapper.findByIds(ids);
	}


	/**
	 * 分页查找
	 * @param e
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public PageInfo<T> findBySelective(E e){
    	if(e.isPagination()){
    		PageHelper.startPage(e.getPageStart(),e.getPageSize());
    		Page<T> list = (Page<T>)this.daoMapper.findBySelective(e);
    		return list.toPageInfo();
    	}
    	return new PageInfo<>(this.daoMapper.findBySelective(e));
    }

	/**
	 * 添加
	 * @param t
	 * @return
	 * @throws KPException
	 */
    @Override
    public int add(T t) throws KPException {
        int count = 0;
        if(t!=null){
            count=daoMapper.add(t);
        }
        return count;
    }


	/**
	 * 根据主键更新,如果一条数据都没有更新成功,则抛出更新异常
	 * @param t
	 * @return
	 * @throws KPException
	 */
	public int updateById(T t) throws KPException {
        int count=0;
        if(t!=null){
            count=daoMapper.update(t);
        }
        if(count==0){
        	throw new KPException(ExceptionKind.UPDATE_E,msg+"["+t.toString()+"]");
        }
        return count;
    }
    
    /**
     * 
     * Title: deleteById
     * Description:    根据主键删除,如果一条数据都没有删除成功,则抛出删除异常
     * @param id
     * @return
     * @throws KPException  
     */
    @Override
    public int deleteById(String id) throws KPException {
        return daoMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
	@Override
	public int deleteByIds(String[] ids) {
		return daoMapper.deleteByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return daoMapper.findBySelective(null);
	}
}
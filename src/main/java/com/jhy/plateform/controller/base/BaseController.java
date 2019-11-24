package com.jhy.plateform.controller.base;


import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.jhy.plateform.anno.ClassInfoAnno;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.domain.base.BaseDomain;
import com.jhy.plateform.exception.ExceptionKind;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.base.BaseQuery;
import com.jhy.plateform.service.base.BaseService;
import com.jhy.plateform.utils.ConstantUtil;
import com.jhy.plateform.utils.JsonModel;
import com.jhy.plateform.utils.Message;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
/**
  * 
  * @ClassName: BaseController
  * @Company: 麦子科技
  * @Description: 公共控制器
  * @author 技术部-刘强峰
  * @date 2016年4月6日 下午6:54:59
  *
  * @param <T>
 */
public abstract class BaseController <T extends BaseDomain,E extends BaseQuery> {
    
	protected BaseService<T,E> baseService;
	
	protected String msg;                       //提示信息
	protected String idName;                    //主键名称
	                                            
	protected String listUI;                    //列表页面
	protected String addUI;                     //添加页面
	protected String editUI;                    //编辑页面
	protected String detailUI;                  //详情页面
	
	protected final String URI;                 //模块
	
	protected String [] filterFields;
	
	protected Class<T> clazz;
	
	protected static String NO_DATA = "noData"; //暂无数据
	  
	public BaseController() {
	    ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
	    clazz= (Class<T>) pt.getActualTypeArguments()[0];
	    if(clazz.isAnnotationPresent(ClassInfoAnno.class)){
	        this.msg=((ClassInfoAnno) clazz.getAnnotation(ClassInfoAnno.class)).msg();
	    	this.idName=((ClassInfoAnno) clazz.getAnnotation(ClassInfoAnno.class)).resourceId();
	    }
	    URI = this.getClass().getAnnotation(RequestMapping.class).value()[0];
	    
	    //处理UI
	    ControllerAnno controllerAnno = this.getClass().getAnnotation(ControllerAnno.class);
	    if(controllerAnno!=null){
	    	this.listUI = controllerAnno.listUI();
	    	this.addUI = controllerAnno.addUI();
	    	this.editUI = controllerAnno.editUI();
	    	this.detailUI = controllerAnno.detailUI();
	    }
	}
	 
	
	/**
	 * 添加瞬时消息
	 * @param redirectAttributes
	 * @param message
	 */
	protected void addFlashMessage(RedirectAttributes redirectAttributes, Message message) {
		if (redirectAttributes != null && message != null) {
			redirectAttributes.addFlashAttribute(ConstantUtil.FLASH_MESSAGE_ATTRIBUTE_NAME, message);
		}
	}

	@RequestMapping(value="/check",method = RequestMethod.GET)
	public @ResponseBody Map<String,Boolean> check(HttpServletRequest request) throws KPException{
		Map<String,Object> param =new HashMap<String,Object>();
		
		Enumeration<String> enu=request.getParameterNames();  
		while(enu.hasMoreElements()){  
		String paramName=(String)enu.nextElement();  
		param.put(paramName,request.getParameter(paramName));
		}  
		int count = this.baseService.check(param);
		
		Map<String,Boolean> result = new HashMap<String, Boolean>();
		result.put("valid",count==0?true:false);
		return result;
	}
	
	/**
	 * 分页查询[返回JSON]
	 * @param e
	 * @param request
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value="", method=RequestMethod.GET, headers="X-Requested-With=XMLHttpRequest")
	public @ResponseBody Map<String,Object> list(E e, HttpServletRequest request) throws KPException{
		Map<String,Object> result = new HashMap<String,Object>();
		PageInfo<T> results = baseService.findBySelective(e);
		result.put("success", true);
		result.put("msg","查询"+msg+"成功");  
		result.put("total", results.getTotal());
		result.put("data", results.getList());
		return result;  
	}
		
 
	@RequestMapping(value="", method=RequestMethod.GET)
    public String list(HttpServletRequest request,ModelMap modelMap) throws KPException {
		return listUI;
	}
		

	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public String findById(@PathVariable String id,ModelMap modelMap){
		T t = baseService.findById(id);
		if(t==null){
			return "redirect:"+URI;
		}
		modelMap.addAttribute(t);
		return this.detailUI;
	}
	
	//保存前方法
	public abstract T beforeSave(ModelMap modelMap,T t) throws KPException;
	
	//打开界面前
	public abstract void beforeSaveUI(ModelMap modelMap,String id) throws KPException;
	 
	/**
	 * 添加界面
	 * @param modelMap
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value={"/new"}, method={RequestMethod.GET})
	public String addUI(ModelMap modelMap) throws KPException {
		try {
			modelMap.addAttribute(this.clazz.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			throw new KPException(ExceptionKind.ACCESS_E,e.getMessage());
		}
		beforeSaveUI(modelMap,null);
		return this.addUI;
	}
	/**
	 * 添加
	 * @param t
	 * @param modelMap
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value="/",method = { RequestMethod.POST })
	public String add(T t,ModelMap modelMap) throws KPException {
		t=beforeSave(modelMap,t);
		this.baseService.add(t);
		return "redirect:" + URI;
	}
	
	/**
	 * 编辑界面 
	 * @param id
	 * @param modelMap
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value = {"/{id}/edit"}, method = { RequestMethod.GET })
	public String editUI(@PathVariable("id") String id, ModelMap modelMap) throws KPException {
		if (StringUtil.isEmpty(id)) {
			throw new KPException(ExceptionKind.PARAM_E,"查询信息时参数id为空");
		}
		BaseDomain t = (BaseDomain) this.baseService.findById(id);
		if (t == null){
			return NO_DATA;
		}
		modelMap.addAttribute(t);
		beforeSaveUI(modelMap,id);
		return this.editUI;
	}

	/**
	 * 更新
	 * @param id
	 * @param modelMap
	 * @param t
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value={"/{id}"}, method={RequestMethod.PUT})
	public String edit(@PathVariable("id") String id, ModelMap modelMap, T t)throws KPException{
		if (StringUtil.isEmpty(id)) {
			throw new KPException(ExceptionKind.PARAM_E);
		}
		t = beforeSave(modelMap, t);
		this.baseService.updateById(t);
		return "redirect:" + URI;
	}

	protected abstract void beforeDelete(String [] ids) throws KPException;
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value={"/{ids}"}, method={RequestMethod.DELETE},headers="X-Requested-With=XMLHttpRequest")
	public @ResponseBody JsonModel delete(@PathVariable("ids") String ids){
		JsonModel jsonModel = new JsonModel();
		try {
			String id[] = ids.split(",");
			beforeDelete(id);
			int result;
			if (id.length > 1) {
				// 批量删除
				result = this.baseService.deleteByIds(id);
			} else {
				//单个删除
				result = this.baseService.deleteById(ids);
			}
			if(result==0){
				jsonModel.setSuccess(false);
				jsonModel.setMsg("删除失败");
			}else{
				jsonModel.setSuccess(true);
				jsonModel.setMsg("成功删除" + result + "条记录");
			}
		} catch (Exception e) {
			jsonModel.setSuccess(false);
			jsonModel.setMsg("删除" + msg + "失败,请重新刷新数据再删除");
		}
		return jsonModel;
	}
}
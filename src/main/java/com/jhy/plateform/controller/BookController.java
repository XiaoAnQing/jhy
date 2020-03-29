package com.jhy.plateform.controller;

import com.github.pagehelper.PageInfo;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.domain.Book;
import com.jhy.plateform.domain.User;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.BookQuery;
import com.jhy.plateform.service.BookService;
import com.jhy.plateform.utils.ConstantUtil;
import com.jhy.plateform.utils.DateUtil;
import com.jhy.plateform.utils.JsonModel;
import com.jhy.plateform.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/books")
@ControllerAnno(addUI = "/book/save", detailUI = "/book/detail", editUI = "/book/save", listUI = "/book/list")
public class BookController{

	@Autowired
	BookService bookService;




	/**
	 * 分页查询[返回JSON]
	 * @param bookQuery
	 * @param request
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value="", method= RequestMethod.GET, headers="X-Requested-With=XMLHttpRequest")
	public @ResponseBody
	Map<String,Object> list(BookQuery bookQuery, HttpServletRequest request) throws KPException{
		Map<String,Object> result;
		result = new HashMap<String,Object>();
		PageInfo<Book> results =null;
		results = bookService.findBySelective(bookQuery);
		result.put("success", true);
		result.put("msg","查询订单成功");
		result.put("total", results.getTotal());
		result.put("data", results.getList());
		return result;
	}


	@RequestMapping(value="", method=RequestMethod.GET)
    public String list(HttpServletRequest request,ModelMap modelMap) throws KPException {
		return "/book/list";
	}



	/**
	 * 添加界面
	 * @param modelMap
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value={"/new"}, method={RequestMethod.GET})
	public String addUI(ModelMap modelMap) throws KPException {
		modelMap.addAttribute(new Book());
		return "/book/save";
	}


	/**
	 * 添加
	 * @param t
	 * @param modelMap
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value="/",method = { RequestMethod.POST })
	public String add(Book t,ModelMap modelMap) throws KPException {
		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
		User user = (User) request.getSession().getAttribute(ConstantUtil.SESSION_KEY);
		t.setNum(StringUtil.formateDate(DateUtil.getCurrentDate(),"yyyyMMddHHmmss")+user.getId());
		//设置跟单信息
		t.setUserId(user.getId());
		bookService.add(t);
		return "redirect:/books";
	}

	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value={"/{ids}"}, method={RequestMethod.DELETE},headers="X-Requested-With=XMLHttpRequest")
	public @ResponseBody
	JsonModel delete(@PathVariable("ids") String ids){
		JsonModel jsonModel = new JsonModel();
		try {
			String id[] = ids.split(",");
			int result;
			if (id.length > 1) {
				// 批量删除
				result = this.bookService.deleteByIds(id);
			} else {
				//单个删除
				result = this.bookService.deleteById(ids);
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
			jsonModel.setMsg("删除订单失败,请重新刷新数据再删除");
		}
		return jsonModel;
	}
}
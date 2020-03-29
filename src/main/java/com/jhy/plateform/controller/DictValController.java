package com.jhy.plateform.controller;

import com.github.pagehelper.PageInfo;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.Dict;
import com.jhy.plateform.domain.DictVal;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.DictValQuery;
import com.jhy.plateform.service.DictValService;
import com.jhy.plateform.utils.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/dictVals")
public class DictValController {

	@Autowired
	DictValService dictValService;

	/**
	 * 分页查询[返回JSON]
	 * @param e
	 * @param request
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value="", method= RequestMethod.GET, headers="X-Requested-With=XMLHttpRequest")
	public @ResponseBody
	Map<String,Object> list(DictValQuery e, HttpServletRequest request) throws KPException{
		Map<String,Object> result = new HashMap<String,Object>();
		PageInfo<DictVal> results = dictValService.findBySelective(e);
		result.put("success", true);
		result.put("msg","查询字典明细成功");
		result.put("total", results.getTotal());
		result.put("data", results.getList());
		return result;
	}


	@RequestMapping(value="", method=RequestMethod.GET)
	public String list(HttpServletRequest request,ModelMap modelMap) throws KPException {
		return "/dictVals/list";
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
				result = dictValService.deleteByIds(id);
			} else {
				//单个删除
				result = dictValService.deleteById(ids);
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
			jsonModel.setMsg("删除字典明细失败,请重新刷新数据再删除");
		}
		return jsonModel;
	}
}
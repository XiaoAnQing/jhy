package com.jhy.plateform.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.jhy.plateform.domain.MaterialType;
import com.jhy.plateform.exception.ExceptionKind;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.MaterialTypeQuery;
import com.jhy.plateform.service.MaterialTypeService;
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
@RequestMapping("materialTypes")
public class MaterialTypeController {

    @Autowired
    MaterialTypeService materialTypeService;

    /**
     * @param materialTypeQuery
     * @param request
     * @return
     * @throws KPException
     */
    @RequestMapping(value="", method= RequestMethod.GET, headers="X-Requested-With=XMLHttpRequest")
    @ResponseBody
    public Map<String,Object> list(MaterialTypeQuery materialTypeQuery, HttpServletRequest request) throws KPException {
        Map<String,Object> result = new HashMap<String,Object>();
        PageInfo<MaterialType> results = materialTypeService.findBySelective(materialTypeQuery);
        result.put("success", true);
        result.put("msg","查询材料类别成功");
        result.put("total", results.getTotal());
        result.put("data", results.getList());
        return result;
    }


    @RequestMapping(value="", method=RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) throws KPException {
        return "materialType/list";
    }

    /**
     * 添加
     * @param materialType
     * @return
     * @throws KPException
     */
    @RequestMapping(value="/",method = { RequestMethod.POST })
    @ResponseBody
    public JsonModel add(MaterialType materialType) throws KPException {
        JsonModel jsonModel = new JsonModel();
        materialTypeService.add(materialType);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("添加材料类别成功");
        return jsonModel;
    }


    /**
     * 更新
     * @param id
     * @param materialType
     * @return
     * @throws KPException
     */
    @RequestMapping(value={"/{id}"}, method={RequestMethod.PUT})
    @ResponseBody
    public JsonModel edit(@PathVariable("id") String id,MaterialType materialType)throws KPException{
        if (StringUtil.isEmpty(id)) {
            throw new KPException(ExceptionKind.PARAM_E);
        }
        JsonModel jsonModel = new JsonModel();
        materialTypeService.updateById(materialType);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("修改材料类别成功");
        return jsonModel;
    }

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
            int result;
            if (id.length > 1) {
                // 批量删除
                result = materialTypeService.deleteByIds(id);
            } else {
                //单个删除
                result = materialTypeService.deleteById(ids);
            }
            if(result==0){
                jsonModel.setMsg("删除失败");
            }else{
                jsonModel.setSuccess(true);
                jsonModel.setMsg("成功删除" + result + "条记录");
            }
        } catch (Exception e) {
            jsonModel.setSuccess(false);
            jsonModel.setMsg("删除材料类别失败,请重新刷新数据再删除");
        }
        return jsonModel;
    }
}

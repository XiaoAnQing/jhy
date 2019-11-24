package com.jhy.plateform.controller;


import com.github.pagehelper.PageInfo;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.Menu;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.MenuQuery;
import com.jhy.plateform.service.MenuService;
import com.jhy.plateform.utils.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/menus")
@ControllerAnno(addUI = "/menu/save", detailUI = "/menu/detail", editUI = "/menu/save", listUI = "/menu/list")
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String list(){
        return "menu/list";
    }

    @RequestMapping(value = "",method = RequestMethod.GET,headers ={"X-Requested-With=XMLHttpRequest"} )
    @ResponseBody
    public JsonModel list(MenuQuery menuQuery){
        JsonModel jsonModel = new JsonModel();
        menuQuery.setPagination(false);
        PageInfo<Menu> pageInfo = menuService.findBySelective(menuQuery);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("查找菜单成功");
        jsonModel.setData(pageInfo.getList());
        jsonModel.setTotal((int)pageInfo.getTotal());
        return jsonModel;
    }

    @RequestMapping(value = "",method = RequestMethod.POST ,headers ={"X-Requested-With=XMLHttpRequest"})
    @ResponseBody
    public JsonModel add(Menu menu){//parent.id
        JsonModel jsonModel = new JsonModel();
		menuService.add(menu);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("添加成功");
        jsonModel.setData(menu.getId());
        return jsonModel;
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT ,headers ={"X-Requested-With=XMLHttpRequest"})
    @ResponseBody
    public JsonModel edit(Menu menu){
        JsonModel jsonModel = new JsonModel();
        menuService.updateById(menu);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("更新成功");
        jsonModel.setData(menu.getId());
        return jsonModel;
    }

    @RequestMapping(value = "{ids}",method = RequestMethod.DELETE,headers ={"X-Requested-With=XMLHttpRequest"})
    @ResponseBody
    public JsonModel deleteByIds(@PathVariable("ids") String ids){
        JsonModel jsonModel = new JsonModel();
        String [] id =  ids.split(",");

        menuService.deleteByIds(id);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("成功删除"+ id.length+"个菜单");
        return jsonModel;
    }
}
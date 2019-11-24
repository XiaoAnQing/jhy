package com.jhy.plateform.controller;

import com.jhy.plateform.domain.Dept;
import com.jhy.plateform.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("depts")
public class DeptController {
    @RequestMapping("info")
    public String  info(String name){
        return  name;
    }

    @RequestMapping("detail")
    public ModelAndView detail(){
        ModelAndView modelAndView = new ModelAndView();
        Dept dept = new Dept();
        dept.setName("test");
        modelAndView.addObject(dept);
        modelAndView.setViewName("detail");
        return modelAndView;
    }
}

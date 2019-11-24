package com.jhy.plateform.listener;

import com.jhy.plateform.domain.Menu;
import com.jhy.plateform.service.MenuService;
import com.jhy.plateform.service.impl.MenuServiceImpl;
import com.jhy.plateform.utils.SpringUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SpringUtil.init(sce.getServletContext());
        MenuService menuService = SpringUtil.getBean("menuServiceImpl");

        List<Menu> menus = menuService.findLevelMenu();
        sce.getServletContext().setAttribute("menus",menus);
    }
}

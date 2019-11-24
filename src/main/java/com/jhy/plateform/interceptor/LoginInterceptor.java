package com.jhy.plateform.interceptor;

import com.jhy.plateform.utils.ConstantUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(true){
            return true;
        }
        HttpSession session = request.getSession();
        if(session.getAttribute(ConstantUtil.SESSION_KEY)==null){
            //没有登录
            //有登录
            String requestURI = request.getRequestURI();
            if(requestURI.startsWith(request.getContextPath()+"/login")){
                return true;
            }
            response.sendRedirect("login");
            return false;
        }else{
            //有登录
            String requestURI = request.getRequestURI();

            if(requestURI.startsWith(request.getContextPath()+"/login")){
                response.sendRedirect("index");
                return false;
            }
            return true;
        }
    }
}

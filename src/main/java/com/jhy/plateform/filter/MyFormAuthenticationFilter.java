/**
 * 
 */
package com.jhy.plateform.filter;
 
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	/*
	 *  重写时注意事项：
	 *      1，没有session。调用FormAuthenticationFilter.onAccessDeny()方法。
	 *      2，没有session，但是是LoginURL。调用AuthenticatingFilter.executeLogin()
	 *                   认证成功，调用 AuthenticatingFilter中 onLoginSuccess(token, subject, request, response);
	 *                   认证失败，调用 AuthenticatingFilter中 onLoginFailure(token, e, request, response);
	 *     						  在认证之前又开始进行了Token认证，所以要重写 createToken方法。
	 *
	 *
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

		//TODO 需要在验证账号和名称之前校验验证码
		// 此处用于校验验证码模块
        if(isAjax(request)){
            if (this.isLoginRequest(request, response)) {
                if (this.isLoginSubmission(request, response)) {//是否来登录
                    System.out.println("Login submission detected.  Attempting to execute login.");
                    return executeLogin(request, response);
                } else {
                    return true;
                }
            } else {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                //TODO 响应JSON数据
                out.println("no session");
                out.flush();
                out.close();
                return false;
            }
        }else{
            return super.onAccessDenied(request,response);
        }
	}

	@Override
	public AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		return createToken(username, password, request, response);
	}


    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token =createToken(request, response);

        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            System.out.println("*******" + msg);
            throw new IllegalStateException(msg);
        }
        try {
            Subject subject = getSubject(request, response);
            subject.login(token);
            return this.onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return this.onLoginFailure(token, e, request, response);
        }
    }


    /**
     * 校验成功
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
	    if(isAjax(request)){
	        response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            return true;
        }else{
  	        return super.onLoginSuccess(token, subject, request, response);
        }
    }


    /**
     * 校验失败
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        if(isAjax(request)){
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            out.println("not user");
            out.flush();
            out.close();
            return false;
        }else{
            return super.onLoginFailure(token,e,request,response);
        }
    }

    /**
     * 判断是否ajax请求
     * @param request
     * @return
     */
    public boolean isAjax(ServletRequest request){
	    HttpServletRequest myRequest = (HttpServletRequest) request;
	    if("XMLHttpRequest".equals(myRequest.getHeader("X-Requested-With"))){
	        return true;
        }else{
	        return true;
        }
    }


    @Bean
    public FilterRegistrationBean registration(MyFormAuthenticationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}
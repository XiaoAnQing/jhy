package com.jhy.plateform.config;

import com.jhy.plateform.listener.MyServletContextListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;

@Configuration //相当于xml
public class SpringConfig {


   @Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
        StringHttpMessageConverter converter  = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Bean
    public  ServletListenerRegistrationBean<MyServletContextListener> registerServletContextListener(){
         ServletListenerRegistrationBean<MyServletContextListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean();
         servletListenerRegistrationBean.setListener(new MyServletContextListener());
         return servletListenerRegistrationBean;
    }
}

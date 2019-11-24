package com.jhy.plateform.config;

import com.jhy.plateform.filter.MyFormAuthenticationFilter;
import com.jhy.plateform.manager.MySessionManager;
import com.jhy.plateform.ream.MyShiroRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.apache.shiro.mgt.SecurityManager;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

//@Configuration
public class ShiroConfig {
   @Bean
   public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {

      ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

      shiroFilterFactoryBean.setSecurityManager(securityManager);

      //设置验证表单拦截器
      Map<String, Filter> authFilterMap = new LinkedHashMap<String,Filter>();
      authFilterMap.put("authc",new MyFormAuthenticationFilter());


      //拦截器.
      Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
      // 使用 ("/static/**", "anon")来配置匿名访问
      filterChainDefinitionMap.put("/static/**", "anon");

      //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了,不用我们自己实现
      filterChainDefinitionMap.put("/logout", "logout");

      //过滤链定义，从上向下顺序执行，一般将/**放在最为下边,一定要按照该规范
      //authc:所有url都必须认证通过才可以访问;
      // anon:所有url都都可以匿名访问
      filterChainDefinitionMap.put("/**", "authc");
      //如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
      shiroFilterFactoryBean.setLoginUrl("/login");
      //登录成功后要跳转的链接
      shiroFilterFactoryBean.setSuccessUrl("/index");

      //未授权界面;
      shiroFilterFactoryBean.setUnauthorizedUrl("/403");
       shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
       shiroFilterFactoryBean.setFilters(authFilterMap);
      return shiroFilterFactoryBean;
   }

   @Bean
   public MyShiroRealm myShiroRealm(){
      MyShiroRealm myShiroRealm = new MyShiroRealm();
      return myShiroRealm;
   }


   @Bean
   public SecurityManager securityManager(){
      DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
      securityManager.setRealm(myShiroRealm());
      //采用自定义的sessionManager
      securityManager.setSessionManager(sessionManager());
      // 自定义缓存实现 使用redis
      securityManager.setCacheManager(shiroCacheManager());
      return securityManager;
   }

   @Bean
   public SessionManager sessionManager() {
	   MySessionManager mySessionManager = new MySessionManager();
	   mySessionManager.setSessionDAO(redisSessionDAO());
	   return mySessionManager;
   }

	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setKeyPrefix("xxx");
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}



	/**
	 * 配置shiro redisManager
	 * <p>
	 * 使用的是shiro-redis开源插件
	 *
	 * @return
	 */
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost("192.168.245.5");
		redisManager.setExpire(3600);

	/*	redisManager.setPort(port);
		redisManager.setTimeout(timeout);
		redisManager.setPassword(password);*/
		return redisManager;
	}


    /**
	 *  开启shiro aop注解支持.
	 *  使用代理方式;所以需要开启代码支持;
	 */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
       AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
       authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
       return authorizationAttributeSourceAdvisor;
    }
 /**
     * shiroCacheManager 缓存 redis实现
     * <p>
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisCacheManager shiroCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setKeyPrefix("permission");
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }
}


package com.jhy.plateform.ream;

import com.jhy.plateform.domain.User;
import com.jhy.plateform.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    @Override
	public String getName() {
		return "customRealm";
	}

	//认证
     @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("验证。。。。。。。。。。。。。。。。。。。。。。");
         //账号
        String loginName = (String)token.getPrincipal();

        //可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.findByLoginName(loginName);
        if(user == null){
            return null;
        }

        //这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                getName()
        );
        return authenticationInfo;
    }


    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("授权。。。。。。。。。。。。。。。。。。。。。。");
        //设置权限信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Object user  = principals.getPrimaryPrincipal();

        System.out.println(user.getClass());
        System.out.println(user);


        //TODO 查找自身的权限信息

        authorizationInfo.addStringPermission("dict:query");
        authorizationInfo.addStringPermission("dict:aa");


        //authorizationInfo.addStringPermission();
        return authorizationInfo;
    }
}
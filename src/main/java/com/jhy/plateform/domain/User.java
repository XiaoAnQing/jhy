package com.jhy.plateform.domain;
import com.jhy.plateform.anno.ClassInfoAnno;
import com.jhy.plateform.domain.base.BaseDomain;

import java.io.Serializable;
import java.math.BigDecimal;
/**
  * 
  * @ClassName: User
  * @Company: 麦子科技
  * @Description: User实体
  * @author 技术部-刘强峰
  * @date 2016年5月1日 下午1:38:35
  *
 */
@ClassInfoAnno(msg="供应商",resourceId="id",dbId = "id")
public class User extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -8013619918670240111L;
    //名称
    private String name;                                      
    //邮箱
    private String email;                                      
    //登录名
    private String loginName;                                      
    //密码
    private String password;                                      


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
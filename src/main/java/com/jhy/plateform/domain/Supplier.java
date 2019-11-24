package com.jhy.plateform.domain;
import com.jhy.plateform.anno.ClassInfoAnno;
import com.jhy.plateform.domain.base.BaseDomain;
import java.math.BigDecimal;
/**
  * 
  * @ClassName: Supplier
  * @Company: 麦子科技
  * @Description: Supplier实体
  * @author 技术部-刘强峰
  * @date 2016年5月1日 下午1:38:35
  *
 */
@ClassInfoAnno(msg="供应商",resourceId="id",dbId = "id")
public class Supplier extends BaseDomain {
    //名称
    private String name;                                      
    //邮箱
    private String email;                                      
    //地址
    private String address;                                      
    //账户
    private String account;                                      
    //联系人
    private String linkName;                                      
    //电话
    private String linePhone;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinePhone() {
        return linePhone;
    }

    public void setLinePhone(String linePhone) {
        this.linePhone = linePhone;
    }
}
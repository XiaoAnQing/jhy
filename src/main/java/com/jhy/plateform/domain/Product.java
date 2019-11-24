package com.jhy.plateform.domain;
import com.jhy.plateform.anno.ClassInfoAnno;
import com.jhy.plateform.domain.base.BaseDomain;
import java.math.BigDecimal;
/**
  * 
  * @ClassName: Product
  * @Company: 麦子科技
  * @Description: Product实体
  * @author 技术部-刘强峰
  * @date 2016年5月1日 下午1:38:35
  *
 */
@ClassInfoAnno(msg="产品",resourceId="id",dbId = "id")
public class Product extends BaseDomain {
    //名称
    private String name;                                      
    //简介
    private String title;                                      
    //产品设计图
    private String detailImg;                                      
    //客户编号
    private String customerNum;                                      
    //产品编号
    private String num;                                      
    //上一次单价
    private BigDecimal oriPrice;  
    //单价
    private BigDecimal price;  
    //脾套
    private String splenicCuff;                                      
    //镜片
    private String glasses;                                      
    //电镀色
    private String electroplatingColour;                                      
    //材料
    private String typeName;                                      
     //商品类别
    private Integer productTypeId;                                    
     //客户
    private Integer customerId;                                    
     //跟单
    private Integer userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailImg() {
        return detailImg;
    }

    public void setDetailImg(String detailImg) {
        this.detailImg = detailImg;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public BigDecimal getOriPrice() {
        return oriPrice;
    }

    public void setOriPrice(BigDecimal oriPrice) {
        this.oriPrice = oriPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSplenicCuff() {
        return splenicCuff;
    }

    public void setSplenicCuff(String splenicCuff) {
        this.splenicCuff = splenicCuff;
    }

    public String getGlasses() {
        return glasses;
    }

    public void setGlasses(String glasses) {
        this.glasses = glasses;
    }

    public String getElectroplatingColour() {
        return electroplatingColour;
    }

    public void setElectroplatingColour(String electroplatingColour) {
        this.electroplatingColour = electroplatingColour;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
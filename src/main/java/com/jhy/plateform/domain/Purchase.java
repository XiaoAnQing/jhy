package com.jhy.plateform.domain;
import com.jhy.plateform.anno.ClassInfoAnno;
import com.jhy.plateform.domain.base.BaseDomain;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
  * 
  * @ClassName: Purchase
  * @Company: 麦子科技
  * @Description: Purchase实体
  * @author 技术部-刘强峰
  * @date 2016年5月1日 下午1:38:35
  *
 */
@ClassInfoAnno(msg="采购订单",resourceId="id",dbId = "id")
public class Purchase extends BaseDomain {
    //编号
    private String num;                                      
    //下单时间]                               
    private Date createDate;
    //交货日期]                               
    private Date endDate;                                        
     //供应商
    private Integer supplierId;                                    
    //供应商
    private String supplierName;                                      
    //总金额
    private BigDecimal price;  
    //支付信息
    private String payInfo;                                      
    //凭据图片
    private String evidenceImg;                                      
    //邮件
    private String email;                                      
    //跟单员
    private String userName;                                      
    //跟单
    private Integer userId;

    //采购明细
    private List<PurchaseItem> purchaseItems = new ArrayList<PurchaseItem>();

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    public String getEvidenceImg() {
        return evidenceImg;
    }

    public void setEvidenceImg(String evidenceImg) {
        this.evidenceImg = evidenceImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }
}
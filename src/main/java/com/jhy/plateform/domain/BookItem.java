package com.jhy.plateform.domain;

import com.jhy.plateform.anno.ClassInfoAnno;
import com.jhy.plateform.domain.base.BaseDomain;

import java.math.BigDecimal;
import java.util.Date;

/**
  * 
  * @ClassName: BookItem
  * @Company: 麦子科技
  * @Description: BookItem实体
  * @author 技术部-刘强峰
  * @date 2016年5月1日 下午1:38:35
  *
 */
@ClassInfoAnno(msg="订单明细",resourceId="id",dbId = "id")
public class BookItem extends BaseDomain {
    //订单
    private String bookNum;                                      
    //名称
    private String name;                                      
    //图片
    private String img;                                      
    //单价
    private BigDecimal price;  
    //总价
    private BigDecimal totalPrice;  
     //数量
    private Integer totalCount;                                    
    //出货日期
    private Date endDate;
     //产品
    private Integer productId;

    public String getBookNum() {
        return bookNum;
    }

    public void setBookNum(String bookNum) {
        this.bookNum = bookNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
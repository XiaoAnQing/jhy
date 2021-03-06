package com.jhy.plateform.domain;
import com.jhy.plateform.anno.ClassInfoAnno;
import com.jhy.plateform.domain.base.BaseDomain;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
/**
  * 
  * @ClassName: Material
  * @Company: 麦子科技
  * @Description: Material实体
  * @author 技术部-刘强峰
  * @date 2016年5月1日 下午1:38:35
  *
 */
@ClassInfoAnno(msg="材料",resourceId="id",dbId = "id")
public class Material extends BaseDomain {
    @NotBlank
    //名称
    private String name;                                      
    //编号
    @NotBlank
    private String num;                                      
    //材料
    @NotBlank
    private String material;                                      
    //工艺
    @NotBlank
    private String technology;                                      
    //原价
    @NotBlank
    private BigDecimal oriPrice;  
    //现价
    @NotBlank
    private BigDecimal price;  
    //尺寸
    @NotBlank
    private String size;                                      
    //单位
    @NotBlank
    private String unit;                                      
    //单位重量
    @NotBlank
    private BigDecimal unitWeight;  
    //图片
    @NotBlank
    private String detailImg;                                      
     //类别
     @NotBlank
    private Integer materialTypeId;                                    
     //供应商
     @NotBlank
    private Integer supplierId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(BigDecimal unitWeight) {
        this.unitWeight = unitWeight;
    }

    public String getDetailImg() {
        return detailImg;
    }

    public void setDetailImg(String detailImg) {
        this.detailImg = detailImg;
    }

    public Integer getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(Integer materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
}
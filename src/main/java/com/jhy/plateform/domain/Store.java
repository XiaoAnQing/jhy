package com.jhy.plateform.domain;
import com.jhy.plateform.anno.ClassInfoAnno;
import com.jhy.plateform.domain.base.BaseDomain;
import java.math.BigDecimal;
/**
  * 
  * @ClassName: Store
  * @Company: 麦子科技
  * @Description: Store实体
  * @author 技术部-刘强峰
  * @date 2016年5月1日 下午1:38:35
  *
 */
@ClassInfoAnno(msg="仓库",resourceId="id",dbId = "id")
public class Store extends BaseDomain {
     //x轴
    private Integer x;                                    
     //y轴
    private Integer y;                                    
     //z轴
    private Integer z;                                    
     //药品数量
    private Integer materialCount;                                    
     //药品id
    private Integer materialId;                                    
    //药品
    private String materialName;                                      
    //状态
    private Byte status;
     //仓库类别
    private Integer storeTypeId;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    public Integer getMaterialCount() {
        return materialCount;
    }

    public void setMaterialCount(Integer materialCount) {
        this.materialCount = materialCount;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    @Override
    public Byte getStatus() {
        return status;
    }

    @Override
    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getStoreTypeId() {
        return storeTypeId;
    }

    public void setStoreTypeId(Integer storeTypeId) {
        this.storeTypeId = storeTypeId;
    }
}
package com.jhy.plateform.domain;
import com.jhy.plateform.anno.ClassInfoAnno;
import com.jhy.plateform.domain.base.BaseDomain;
import java.math.BigDecimal;
/**
  * 
  * @ClassName: StoreType
  * @Company: 麦子科技
  * @Description: StoreType实体
  * @author 技术部-刘强峰
  * @date 2016年5月1日 下午1:38:35
  *
 */
@ClassInfoAnno(msg="仓库类别",resourceId="id",dbId = "id")
public class StoreType extends BaseDomain {
    //名称
    private String name;                                      
    //编号
    private String num;                                      
     //长度
    private Integer width;                                    
     //高度
    private Integer height;                                    
     //行
    private Integer row;                                    
     //列
    private Integer col;                                    
     //z轴
    private Integer z;                                    
    //状态
    private Byte status;

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

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    @Override
    public Byte getStatus() {
        return status;
    }

    @Override
    public void setStatus(Byte status) {
        this.status = status;
    }
}
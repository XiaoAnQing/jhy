package com.jhy.plateform.domain;
import com.jhy.plateform.anno.ClassInfoAnno;
import com.jhy.plateform.domain.base.BaseDomain;
import java.math.BigDecimal;
/**
  * 
  * @ClassName: Station
  * @Company: 麦子科技
  * @Description: Station实体
  * @author 技术部-刘强峰
  * @date 2016年5月1日 下午1:38:35
  *
 */
@ClassInfoAnno(msg="工序",resourceId="id",dbId = "id")
public class Station extends BaseDomain {
    //工序名
    private String name;                                      
    //图标
    private String icon;                                      
     //主管
    private Integer managerId;
    //位置
    private String position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer mangerId) {
        this.managerId = managerId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
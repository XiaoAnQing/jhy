package com.jhy.plateform.domain;
import com.jhy.plateform.anno.ClassInfoAnno;
import com.jhy.plateform.domain.base.BaseDomain;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
/**
  * 
  * @ClassName: ProductType
  * @Company: 麦子科技
  * @Description: ProductType实体
  * @author 技术部-刘强峰
  * @date 2016年5月1日 下午1:38:35
  *
 */
@ClassInfoAnno(msg="产品类别",resourceId="id",dbId = "id")
public class ProductType extends BaseDomain {
    //名称
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
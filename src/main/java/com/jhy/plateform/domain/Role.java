package com.jhy.plateform.domain;
import com.jhy.plateform.anno.ClassInfoAnno;
import com.jhy.plateform.domain.base.BaseDomain;
import java.math.BigDecimal;
/**
  * 
  * @ClassName: Role
  * @Company: 麦子科技
  * @Description: Role实体
  * @author 技术部-刘强峰
  * @date 2016年5月1日 下午1:38:35
  *
 */
@ClassInfoAnno(msg="角色",resourceId="id",dbId = "id")
public class Role extends BaseDomain {
    //XXXX
    private String name;                                      
}
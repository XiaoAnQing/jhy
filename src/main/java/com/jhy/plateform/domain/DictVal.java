package com.jhy.plateform.domain;

import com.jhy.plateform.anno.ClassInfoAnno;
import com.jhy.plateform.domain.base.BaseDomain;

/**
  * 
  * @ClassName: DictVal
  * @Company: 麦子科技
  * @Description: DictVal实体
  * @author 技术部-刘强峰
  * @date 2016年5月1日 下午1:38:35
  *
 */
@ClassInfoAnno(msg="字典明细",resourceId="id",dbId = "id")
public class DictVal extends BaseDomain {
    //名称
    private String name;                                      
    //值
    private String value;      
    
    private Dict dict;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Dict getDict() {
		return dict;
	}
	public void setDict(Dict dict) {
		this.dict = dict;
	}
}
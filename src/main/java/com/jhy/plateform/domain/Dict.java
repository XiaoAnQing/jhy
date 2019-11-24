package com.jhy.plateform.domain;
import com.jhy.plateform.anno.ClassInfoAnno;
import com.jhy.plateform.domain.base.BaseDomain;
import java.util.ArrayList;
import java.util.List;
/**
  * 
  * @ClassName: Dict
  * @Company: 麦子科技
  * @Description: Dict实体
  * @author 技术部-刘强峰
  * @date 2016年5月1日 下午1:38:35
  *
 */
@ClassInfoAnno(msg="字典",resourceId="id",dbId = "id")
public class Dict extends BaseDomain {
    //名称
    private String name;                                      
     //类型
    private Integer type;                                    
    //备注
    private String note;
    
    private List<DictVal> dictVals = new ArrayList<DictVal>();
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<DictVal> getDictVals() {
		return dictVals;
	}
	public void setDictVals(List<DictVal> dictVals) {
		this.dictVals = dictVals;
	}   
}
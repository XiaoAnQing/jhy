package com.jhy.plateform.domain;
import com.jhy.plateform.anno.ClassInfoAnno;
import com.jhy.plateform.domain.base.BaseDomain;
import java.math.BigDecimal;
import java.util.Date;

/**
  * 
  * @ClassName: TaskLog
  * @Company: 麦子科技
  * @Description: TaskLog实体
  * @author 技术部-刘强峰
  * @date 2016年5月1日 下午1:38:35
  *
 */
@ClassInfoAnno(msg="工序",resourceId="id",dbId = "id")
public class TaskLog extends BaseDomain {
     //完成数
    private Integer totalCount;                                    
     //合格数
    private Integer goodCount;                                    
     //失败数
    private Integer badCount;                                    
    //统计者
    private String userName;                                      
     //统计者
    private Integer userId;                                    
     //任务
    private Integer taskId;                                    
    //统计时间]                               
    private Date ceateDate;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getBadCount() {
        return badCount;
    }

    public void setBadCount(Integer badCount) {
        this.badCount = badCount;
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

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Date getCeateDate() {
        return ceateDate;
    }

    public void setCeateDate(Date ceateDate) {
        this.ceateDate = ceateDate;
    }
}
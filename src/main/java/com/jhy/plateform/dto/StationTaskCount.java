package com.jhy.plateform.dto;

import com.jhy.plateform.domain.Station;
import com.jhy.plateform.query.StationQuery;

public class StationTaskCount extends Station {
    private Integer taskCount;

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }
}

package com.jcohy.scis.service;

import com.jcohy.scis.model.WorkingHour;

import java.util.List;

public interface WorkingHourService {
    /**
     *  员工工时列表
     *
     * @return
     */
    List<WorkingHour> getWorkingHourList();
}

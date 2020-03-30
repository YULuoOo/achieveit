package com.jcohy.scis.service;

import com.jcohy.scis.model.WorkingHour;

import java.util.Date;
import java.util.List;

public interface WorkingHourService {
    /**
     *  员工工时列表
     *
     * @return
     */
    List<WorkingHour> getWorkingHourList();

    /**
     * 提交工时
     *
     * @return
     * @throws Exception
     */
    int createWorkingHour(Integer staff_id, String work_content, Date work_date, float work_length) throws Exception;

}

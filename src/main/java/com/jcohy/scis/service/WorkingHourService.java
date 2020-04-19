package com.jcohy.scis.service;

import com.jcohy.scis.model.Ach_project;
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
     *  员工工时列表
     *
     * @return
     */
    List<WorkingHour> getWorkingHourListByStaffId(Integer staffId);

    /**
     * 提交工时
     *
     * @return
     * @throws Exception
     */
    int createWorkingHour(Integer staff_id, String work_content, Date work_date, float work_length,String state) throws Exception;

    /**
     * 删除工时
     * @param id
     */
    void delete(Integer id);

    /**
     * 查看工时
     * @param id
     */
    WorkingHour getWorkingHour(Integer id);

    /**
     * 编辑工时
     *
     * @return
     * @throws Exception
     */
    int updateWorkingHour(Integer staff_id, String work_content, Date work_date, float work_length, Integer id) throws Exception;


    /**
     * 通过/拒绝审批
     *
     * @return
     * @throws Exception
     */
    int updateWorkingHour(Integer id, String state) throws Exception;
}
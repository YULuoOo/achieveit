package com.jcohy.scis.service.impl;

import com.jcohy.scis.exception.ServiceException;
import com.jcohy.scis.model.Ach_project;
import com.jcohy.scis.model.WorkingHour;
import com.jcohy.scis.repository.AchProjectRepository;
import com.jcohy.scis.repository.WorkingHourRepository;
import com.jcohy.scis.service.AchProjectService;
import com.jcohy.scis.service.WorkingHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WorkingHourServiceImpl implements WorkingHourService {
    @Autowired
    private WorkingHourRepository workingHourRepository;


    @Override
    public List<WorkingHour> getWorkingHourList() {
        return workingHourRepository.getWorkingHourList();
    }

    @Override
    public List<WorkingHour> getWorkingHourListByStaffId(Integer staffId)
    {
        return workingHourRepository.getWorkingHourListByStaffId(staffId);
    }

    @Override
    public int createWorkingHour(Integer staff_id, String work_content, Date work_date, float work_length, String state) throws Exception {
        return workingHourRepository.createWorkingHour(staff_id,work_content,work_date,work_length,state);
    }
    @Override
    public void delete(Integer id) {
        if(id == null){
            throw new ServiceException("主键不能为空");
        }
        workingHourRepository.deleteById(id);
    }

    @Override
    public WorkingHour getWorkingHour(Integer id) {
        if(id == null){
            throw new ServiceException("主键不能为空");
        }
        return workingHourRepository.getOne(id);
    }

    @Override
    public int updateWorkingHour(Integer staff_id, String work_content, Date work_date, float work_length, Integer id) throws Exception {
        return workingHourRepository.updateWorkingHour(staff_id, work_content, work_date, work_length,id);
    }

    @Override
    public int updateWorkingHour(Integer id, String state) throws Exception
    {
        return workingHourRepository.updateWorkingHour(id,state);
    }

}
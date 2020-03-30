package com.jcohy.scis.service.impl;

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
    public int createWorkingHour(Integer staff_id, String work_content, Date work_date, float work_length) throws Exception {
        return workingHourRepository.createWorkingHour(staff_id,work_content,work_date,work_length);
    }
}

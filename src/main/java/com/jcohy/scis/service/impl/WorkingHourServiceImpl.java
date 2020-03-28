package com.jcohy.scis.service.impl;

import com.jcohy.scis.model.WorkingHour;
import com.jcohy.scis.repository.AchProjectRepository;
import com.jcohy.scis.repository.WorkingHourRepository;
import com.jcohy.scis.service.AchProjectService;
import com.jcohy.scis.service.WorkingHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingHourServiceImpl implements WorkingHourService {
    @Autowired
    private WorkingHourRepository workinghourRepository;


    @Override
    public List<WorkingHour> getWorkingHourList() {
        return workinghourRepository.getWorkingHourList();
    }
}

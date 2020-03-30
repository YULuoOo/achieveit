package com.jcohy.scis.service.impl;

import com.jcohy.scis.exception.ServiceException;
import com.jcohy.scis.model.Staff;
import com.jcohy.scis.model.Teacher;
import com.jcohy.scis.repository.StaffRepository;
import com.jcohy.scis.repository.TeacherRepository;
import com.jcohy.scis.service.StaffService;
import com.jcohy.scis.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiac on 2018/4/2.
 * Description  :
 */
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;


    @Override
    public Staff login(Long num, String password) throws Exception {
        return staffRepository.findStaffByNum(num);
    }

    @Override
    public int register(Long num, String name ,String password, String sex, String title) throws Exception {
        return staffRepository.register(num,name,password,sex,title);
    }


    @Override
    public Staff findByNum(Long num) {
        return staffRepository.findStaffByNum(num);
    }


    @Override
    public Staff findByName(String name) {
        return staffRepository.findStaffByName(name);
    }


    @Override
    public void updatePassword(Staff user) {
        staffRepository.saveAndFlush(user);
    }

    @Override
    public Staff findById(Integer id) {
        return staffRepository.getOne(id);
    }
}

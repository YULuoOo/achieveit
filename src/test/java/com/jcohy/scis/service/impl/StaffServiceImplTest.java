package com.jcohy.scis.service.impl;

import com.jcohy.scis.ScisApplication;
import com.jcohy.scis.model.Staff;
import com.jcohy.scis.repository.StaffRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ScisApplication.class)

public class StaffServiceImplTest {

    @Autowired
    private StaffRepository staffRepository;

    @Test
    public void login() {
        Staff staff = staffRepository.findStaffByNum(10001L);
        assert(staff.getPassword().equals("123456"));
    }

    @Test
    public void register() {
    }

    @Test
    public void findByNum() {
        Staff staff = staffRepository.findStaffByNum(10001L);
        assert(staff.getId().equals(1));
    }

    @Test
    public void findByName() {
        Staff staff = staffRepository.findStaffByName("员工1");
        assert(staff.getId().equals(1));
    }

    @Test
    public void updatePassword() {
    }
}
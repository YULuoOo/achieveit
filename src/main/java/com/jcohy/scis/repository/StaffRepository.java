package com.jcohy.scis.repository;

import com.jcohy.scis.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by jiac on 2018/4/2.
 * Description  :
 */
public interface StaffRepository extends JpaRepository<Staff,Integer> {

    Staff findStaffByNum(Long num);

    Staff findStaffByName(String name);

    @Transactional
    @Modifying
    @Query(value = "insert into staff (num,name,password,sex,title) values(?1,?2,?3,?4,?5)",nativeQuery = true)
    int register(Long num, String name, String password, String sex, String title);

    @Override
    Staff getOne(Integer integer);
}

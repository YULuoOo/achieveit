package com.jcohy.scis.repository;

import com.jcohy.scis.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by jiac on 2018/4/2.
 * Description  :
 */
public interface StudentRepository  extends JpaRepository<Student,Integer> {

    Student findAdminByNum(Long num);

    Student findAdminByName(String name);

    @Transactional
    @Modifying
    @Query(value = "insert into student (num,name,password,sex) values(?1,?2,?3,?4)",nativeQuery = true)
    int register(Long num, String name, String password, String sex);


}

package com.jcohy.scis.repository;

import com.jcohy.scis.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by jiac on 2018/4/2.
 * Description  :
 */
public interface TeacherRepository  extends JpaRepository<Teacher,Integer> {

    Teacher findTeacherByNum(Long num);

    Teacher findTeacherByName(String name);

    @Transactional
    @Modifying
    @Query(value = "insert into teacher (num,name,password,sex) values(?1,?2,?3,?4)",nativeQuery = true)
    int register(Long num, String name, String password, String sex);
}

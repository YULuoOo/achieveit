package com.jcohy.scis.repository;

import com.jcohy.scis.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by jiac on 2018/4/2.
 * Description  :
 */
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Admin findAdminByNum(Long num);

    Admin findAdminByName(String name);

    @Transactional
    @Modifying
    @Query(value = "insert into admin (num,name,password,sex) values(?1,?2,?3,?4)",nativeQuery = true)
    int register(Long num, String name, String password, String sex);

}

package com.jcohy.scis.service;

import com.jcohy.scis.exception.ServiceException;
import com.jcohy.scis.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by jiac on 2017/12/18 09:22.
 * ClassName  : UserService
 * Description  :
 */

public interface StaffService {
    /**
     * 用户登录
     * @param num  管理员编号
     * @param password
     * @return
     * @throws Exception
     */
    Staff login(Long num, String password) throws Exception;

    /**
     * 用户注册
     * @param num
     * @param password
     * @return
     * @throws Exception
     */
    int register(Long num, String name, String password, String sex, String title,String email) throws Exception;

    Staff findByNum(Long num);

    /**
     * 根据name查询
     * @param name
     * @return
     */
    Staff findByName(String name);

    /**
     * 修改用户密码
     * @param user
     */
    void updatePassword(Staff user);


    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Staff findById(Integer id);

}

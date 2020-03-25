package com.jcohy.scis.service;

import com.jcohy.scis.model.Ach_project;
import com.jcohy.scis.model.Staff;

import java.util.Date;
import java.util.List;

/**
 * Created by 蛟川小盆友 on 2020/3/17.
 */
public interface AchProjectService {

    /**
     *  项目列表
     * @return
     */
    List<Ach_project> getAchProjectList();

    /**
     *  项目成员列表
     * @return
     */
    List<String> getProjectMemberList(Integer id);
    /**
     * 申请立项
     *
     * @return
     * @throws Exception
     */
    int createProject(String name, String desc, String tech, String area, String func, int status, Date enddate, Date startdate) throws Exception;

    /**
     * 删除项目
     * @param id
     */
    void delete(Integer id);

    /**
     * 查看项目
     * @param id
     */
    Ach_project getAchProject(Integer id);

    /**
     * 编辑项目
     *
     * @return
     * @throws Exception
     */
    int updateProject(String name, String desc, String tech, String area, String func,  Date enddate, Date startdate, Integer id) throws Exception;
}

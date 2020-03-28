package com.jcohy.scis.service;

import com.jcohy.scis.model.Ach_project;
import com.jcohy.scis.model.Staff;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
     *  项目需审批列表
     * @return
     */
    List<Ach_project> getAchProjectProcessList(Integer status);

    /**
     *  项目成员名字列表
     * @return
     */
    List<String> getProjectMemberList(Integer pro_id);
    /**
     *  非项目成员的其他员工列表
     * @return
     */
    List<Map<String,Object>> getOtherStaffs(Integer pro_id);
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

    /**
     * 更新status
     */
    int updateStatus(Integer status,Integer id);

    /**
     * 添加项目成员
     */
    int updateMembers(Integer project_id, Integer staff_id);
}

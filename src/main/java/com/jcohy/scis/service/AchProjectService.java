package com.jcohy.scis.service;

import com.jcohy.scis.model.Ach_project;
import com.jcohy.scis.model.Project;

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
     * 申请立项
     *
     * @return
     * @throws Exception
     */
    int createProject(String name, String desc, String tech, String area, String func, int status, Date enddate, Date startdate) throws Exception;

}

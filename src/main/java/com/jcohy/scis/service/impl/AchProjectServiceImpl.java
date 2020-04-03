package com.jcohy.scis.service.impl;

import com.jcohy.scis.exception.ServiceException;
import com.jcohy.scis.model.Ach_project;
import com.jcohy.scis.model.Staff;
import com.jcohy.scis.repository.AchProjectRepository;
import com.jcohy.scis.service.AchProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 蛟川小盆友 on 2020/3/17.
 */
@Service
public class AchProjectServiceImpl implements AchProjectService{

    @Autowired
    private AchProjectRepository achProjectRepository;

    @Override
    public List<Ach_project> getAchProjectList() {
        return achProjectRepository.getAchProjectList();
    }

    @Override
    public List<Ach_project> getAchProjectProcessList(Integer status) {
        return achProjectRepository.getAchProjectProcessList(status);
    }

    @Override
    public List<String> getProjectMemberList(Integer pro_id) {
        if(pro_id == null){
            throw new ServiceException("主键不能为空");
        }
        return achProjectRepository.getProjectMemberList(pro_id);
    }

    @Override
    public List<Ach_project> getUserProjectList(Integer staff_id) {
        if(staff_id == null){
            throw new ServiceException("主键不能为空");
        }
        return achProjectRepository.getUserProjectList(staff_id);
    }

    @Override
    public List<Map<String,Object>> getOtherStaffs(Integer pro_id) {
        if(pro_id == null){
            throw new ServiceException("主键不能为空");
        }
        return achProjectRepository.getOtherStaffs(pro_id);
    }

    @Override
    public int createProject(String name, String desc, String tech, String area, String func, int status, Date enddate, Date startdate) throws Exception {
        return achProjectRepository.createProject(name,desc,tech,area,func,status,enddate,startdate);
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            throw new ServiceException("主键不能为空");
        }
        achProjectRepository.deleteById(id);
    }

    @Override
    public int updateProject(String name, String desc, String tech, String area, String func,  Date enddate, Date startdate, Integer id) throws Exception {
        return achProjectRepository.updateProject(name,desc,tech,area,func,enddate,startdate,id);
    }

    @Override
    public Ach_project getAchProject(Integer id)
    {
        if(id == null){
            throw new ServiceException("主键不能为空");
        }
        return achProjectRepository.getOne(id);
    }

    @Override
    public Ach_project getAchProjectByName(String name)
    {
        if(name == null){
            throw new ServiceException("名字不能为空");
        }
        return achProjectRepository.getProjectByName(name);
    }

    @Override
    public int updateStatus(Integer status, Integer id) {
        if(id == null){
            throw new ServiceException("主键不能为空");
        }
        return achProjectRepository.updateStatus(status,id);
    }

    @Override
    public int updateMembers(Integer project_id, Integer staff_id,String staff_role) {
        return achProjectRepository.updateMembers(project_id, staff_id,staff_role);
    }
}

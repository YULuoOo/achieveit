package com.jcohy.scis.service.impl;

import com.jcohy.scis.exception.ServiceException;
import com.jcohy.scis.model.Ach_project;
import com.jcohy.scis.repository.AchProjectRepository;
import com.jcohy.scis.repository.ProjectRepository;
import com.jcohy.scis.service.AchProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public Ach_project getAchProject(Integer id)
    {
        if(id == null){
            throw new ServiceException("主键不能为空");
        }
        return achProjectRepository.getOne(id);
    }
}

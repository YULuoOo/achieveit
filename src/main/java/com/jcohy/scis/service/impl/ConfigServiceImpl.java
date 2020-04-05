package com.jcohy.scis.service.impl;

import com.jcohy.scis.exception.ServiceException;
import com.jcohy.scis.model.Config;
import com.jcohy.scis.repository.ConfigRepository;
import com.jcohy.scis.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigRepository configRepository;


    @Override
    public int createConfig(Integer project_id, String git, String root, String size) {
        return configRepository.createConfig(project_id,git,root,size);
    }

    @Override
    public Config getOne(Integer id) {
        if(id == null){
            throw new ServiceException("主键不能为空");
        }
        return configRepository.getOne(id);
    }

    @Override
    public int updateConfig(Integer project_id, String git, String root, String size) {
        System.out.println(git+root+size);
        return configRepository.updateConfig(project_id,git,root,size);
    }
}

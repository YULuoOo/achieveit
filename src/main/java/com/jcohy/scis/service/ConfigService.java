package com.jcohy.scis.service;

import com.jcohy.scis.model.Config;


public interface ConfigService {

    int createConfig(Integer project_id, String git, String root, String size);

    Config getOne(Integer integer);

    int updateConfig(Integer project_id, String git, String root, String size);


}

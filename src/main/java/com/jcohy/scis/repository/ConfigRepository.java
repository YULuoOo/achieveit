package com.jcohy.scis.repository;

import com.jcohy.scis.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ConfigRepository extends JpaRepository<Config,Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into config(project_id,giturl,root,disk_size) values(?1,?2,?3,?4)",nativeQuery = true)
    int createConfig(Integer project_id, String git, String root, String size);

    @Query(value = "select * from config where project_id = ?1",nativeQuery = true)
    Config getOne(Integer integer);

    @Transactional
    @Modifying
    @Query(value = "UPDATE config SET giturl=?2,root=?3,disk_size=?4 WHERE project_id=?1",nativeQuery = true)
    int updateConfig(Integer project_id, String git, String root, String size);


}

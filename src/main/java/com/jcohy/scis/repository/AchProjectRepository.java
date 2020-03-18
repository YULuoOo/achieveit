package com.jcohy.scis.repository;

import com.jcohy.scis.model.Ach_project;
import com.jcohy.scis.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by 蛟川小盆友 on 2020/3/17.
 */
public interface AchProjectRepository extends JpaRepository<Ach_project,Integer> {

    @Query(value = "select * from ach_project", nativeQuery = true)
    List<Ach_project> getAchProjectList();

    @Transactional
    @Modifying
    @Query(value = "insert into ach_project (pro_name,pro_desc,pro_tech,pro_area,pro_func,pro_status,pro_enddate,pro_startdate) values(?1,?2,?3,?4,?5,?6,?7,?8)",nativeQuery = true)
    int createProject(String name, String desc, String tech, String area, String func, int status, Date enddate, Date startdate);
}

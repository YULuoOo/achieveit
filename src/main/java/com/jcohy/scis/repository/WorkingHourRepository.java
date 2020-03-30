package com.jcohy.scis.repository;

import com.jcohy.scis.model.Ach_project;
import com.jcohy.scis.model.WorkingHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface WorkingHourRepository extends JpaRepository<WorkingHour,Integer> {
    @Query(value = "select * from workinghour", nativeQuery = true)
    List<WorkingHour> getWorkingHourList();

    @Transactional
    @Modifying
    @Query(value = "insert into workinghour(staff_id,work_content,work_date,work_length) values(?1,?2,?3,?4)",nativeQuery = true)
    int createWorkingHour(Integer staff_id, String work_content, Date work_date, float work_length);
}

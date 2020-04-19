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

    @Query(value = "select * from workinghour where staff_id=?1", nativeQuery = true)
    List<WorkingHour> getWorkingHourListByStaffId(Integer staffId);

    @Transactional
    @Modifying
    @Query(value = "insert into workinghour(staff_id,work_content,work_date,work_length,state) values(?1,?2,?3,?4,?5)",nativeQuery = true)
    int createWorkingHour(Integer staff_id, String work_content, Date work_date, float work_length,String state);

    @Query(value = "select * from workinghour where id = ?1",nativeQuery = true)
    WorkingHour getOne(Integer integer);

    @Transactional
    @Modifying
    @Query(value = "UPDATE workinghour SET staff_id=?1,work_content=?2,work_date=?3,work_length=?4 WHERE id=?5",nativeQuery = true)
    int updateWorkingHour(Integer staff_id, String work_content, Date work_date, float work_length, Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE workinghour SET state=?2 WHERE id=?1",nativeQuery = true)
    int updateWorkingHour(Integer id,String state);
}
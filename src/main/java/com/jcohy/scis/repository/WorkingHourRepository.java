package com.jcohy.scis.repository;

import com.jcohy.scis.model.Ach_project;
import com.jcohy.scis.model.WorkingHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkingHourRepository extends JpaRepository<WorkingHour,Integer> {
    @Query(value = "select * from workinghour", nativeQuery = true)
    List<WorkingHour> getWorkingHourList();
}

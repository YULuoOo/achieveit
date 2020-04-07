package com.jcohy.scis.repository;

import com.jcohy.scis.model.Ach_project;
import com.jcohy.scis.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 蛟川小盆友 on 2020/3/17.
 */
public interface AchProjectRepository extends JpaRepository<Ach_project,Integer> {

    @Query(value = "select * from ach_project", nativeQuery = true)
    List<Ach_project> getAchProjectList();

    @Query(value = "select * from ach_project where pro_status >= ?1", nativeQuery = true)
    List<Ach_project> getAchProjectProcessList(Integer status);

    @Query(value = "select st.name from staff st inner join staff_project sp on st.id = sp.staff_id where sp.pro_id = ?1", nativeQuery = true)
    List<String> getProjectMemberList(Integer integer);

    @Query(value = "select * from ach_project pj inner join staff_project sp on pj.id = sp.pro_id where sp.staff_id = ?1", nativeQuery = true)
    List<Ach_project> getUserProjectList(Integer integer);

    @Query(value = "select id,num,name from staff where not exists (select * from staff_project where staff.id =staff_project.staff_id and pro_id = ?1)", nativeQuery = true)
    List<Map<String,Object>> getOtherStaffs(Integer integer);

    @Query(value = "select st.id,st.num,st.name,sp.staff_role from staff st inner join staff_project sp on st.id = sp.staff_id where sp.pro_id = ?1", nativeQuery = true)
    List<Map<String,Object>> getProjectStaffs(Integer integer);

    @Query(value = "select * from ach_project where id = ?1",nativeQuery = true)
    Ach_project getOne(Integer integer);

    @Transactional
    @Modifying
    @Query(value = "insert into ach_project (pro_name,pro_desc,pro_tech,pro_area,pro_func,pro_status,pro_enddate,pro_startdate) values(?1,?2,?3,?4,?5,?6,?7,?8)",nativeQuery = true)
    int createProject(String name, String desc, String tech, String area, String func, int status, Date enddate, Date startdate);

    @Query(value = "select * from ach_project where pro_name = ?1",nativeQuery = true)
    Ach_project getProjectByName(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ach_project SET pro_name=?1,pro_desc=?2,pro_tech=?3,pro_area=?4,pro_func=?5,pro_enddate=?6,pro_startdate=?7 WHERE id=?8",nativeQuery = true)
    int updateProject(String name, String desc, String tech, String area, String func,  Date enddate, Date startdate, Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ach_project SET pro_status=?1 WHERE id=?2",nativeQuery = true)
    int updateStatus(Integer status, Integer id);

    @Transactional
    @Modifying
    @Query(value = "insert into staff_project (pro_id,staff_id,staff_role) values(?1,?2,?3)",nativeQuery = true)
    int updateMembers(Integer project_id, Integer staff_id,String staff_role);

    @Transactional
    @Modifying
    @Query(value = "UPDATE staff_project SET  staff_role=?3 WHERE pro_id=?1 and staff_id=?2", nativeQuery = true)
    int updateMembersRole(Integer project_id, Integer staff_id,String staff_role);
}

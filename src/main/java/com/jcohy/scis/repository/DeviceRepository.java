package com.jcohy.scis.repository;

import com.jcohy.scis.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DeviceRepository extends JpaRepository<Device,Integer> {

    @Query(value = "select * from device", nativeQuery = true)
    List<Device> getDeviceList();

    @Query(value = "select * from device where state ='可借用'", nativeQuery = true)
    List<Device> getBorrowableDeviceList();

    @Query(value = "select * from device where owner_id = ?1", nativeQuery = true)
    List<Device> getDeviceListByOwnerId(Integer ownerId);

    @Transactional
    @Modifying
    @Query(value = "insert into device (name,category,owner_id,owner,state,`condition`,borrower) values(?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    int createDevice(String name, String category, Integer ownerId, String owner,String state, String condition,String borrower);

    @Query(value = "select db.id,db.device_id,s.name as borrower,db.state,db.borrow_date,db.return_date,db.condition,db.detail  from device d inner join device_borrow db inner join staff s on d.id = db.device_id and s.id = db.borrower_id where d.id = ?1", nativeQuery = true)
    List<Map<String, Object>> getDeviceBorrowList(Integer deviceId);

    @Query(value = "select db.id,db.device_id,d.name,d.category,d.owner,db.state,db.borrow_date,db.return_date,db.condition,db.detail,d.borrower from device d inner join device_borrow db on d.id = db.device_id where db.borrower_id = ?1 and (db.state = '借用中' or db.state = '借用审核中' or db.state = '归还审核中')", nativeQuery = true)
    List<Map<String, Object>> getDeviceListByBorrowerId(Integer borrowerId);

    @Transactional
    @Modifying
    @Query(value = "insert into device_borrow (device_id,borrower_id,state,borrow_date,return_date,`condition`,detail) values(?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    int createBorrowDeviceRequest(Integer deviceId, Integer borrowerId, String state,Date borrowDate, Date returnDate,String condition, String detail);

    @Transactional
    @Modifying
    @Query(value = "UPDATE device SET state=?3,borrower = (select `name` from staff where id=?2) WHERE id = ?1",nativeQuery = true)
    int updateDevice(Integer deviceId, Integer borrowerId, String state);

    @Transactional
    @Modifying
    @Query(value = "UPDATE device SET state=?2 WHERE id = ?1",nativeQuery = true)
    int updateDevice(Integer deviceId, String state);

    @Transactional
    @Modifying
    @Query(value = "UPDATE device SET state=?2,`condition`=?3 WHERE id = ?1",nativeQuery = true)
    int updateDevice(Integer deviceId, String state,String condition);

    @Transactional
    @Modifying
    @Query(value = "UPDATE device SET state=?2,`condition`=?3,borrower=?4 WHERE id = ?1",nativeQuery = true)
    int updateDevice(Integer deviceId, String state,String condition,String borrower);

    @Transactional
    @Modifying
    @Query(value = "UPDATE device_borrow SET state=?2,return_date=?3,`condition`=?4,detail=?5 WHERE id = ?1",nativeQuery = true)
    int updateDeviceBorrowRecord(Integer id, String state, Date returnDate, String condition, String detail);

    @Transactional
    @Modifying
    @Query(value = "UPDATE device_borrow SET state=?2 WHERE id = ?1",nativeQuery = true)
    int updateDeviceBorrowRecord(Integer id, String state);
}

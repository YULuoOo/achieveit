package com.jcohy.scis.service;

import com.jcohy.scis.model.Device;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DeviceService
{
    /**
     *  设备列表
     *
     * @return
     */
    List<Device> getDeviceList();

    /**
     *  可借用的设备列表
     *
     * @return
     */
    List<Device> getBorrowableDeviceList();
    /**
     *  通过ownerId获取设备列表
     *
     * @return
     */
    List<Device> getDeviceListByOwnerId(Integer ownerId);

    /**
     * 添加设备
     *
     * @return
     * @throws Exception
     */
    int createDevice(String name,String category,Integer ownerId, String owner,String state, String condition, String borrower) throws Exception;

    /**
     * 删除设备
     * @param id
     */
    void delete(Integer id);

    /**
     *  获取设备的借用记录
     *
     * @return
     */
    List<Map<String,Object>> getDeviceBorrowList(Integer deviceId);

    /**
     *  通过borrowId获取设备列表
     *
     * @return
     */
    List<Map<String,Object>> getDeviceListByBorrowerId(Integer borrowerId);

    /**
     * 申请借用设备
     *
     * @return
     * @throws Exception
     */
    int createBorrowDeviceRequest(Integer deviceId, Integer borrowerId, String state,Date borrowDate, Date returnDate,String condition, String detail) throws Exception;

    /**
     * 更新设备借用记录
     */
    int updateDeviceBorrowRecord(Integer id,Integer deviceId,String state,Date returnDate,String condition,String detail);

    /**
     * 更新设备借用记录
     */
    int updateDeviceBorrowRecord(Integer id,Integer deviceId,String state);
}

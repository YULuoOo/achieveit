package com.jcohy.scis.service.impl;

import com.jcohy.scis.exception.ServiceException;
import com.jcohy.scis.model.Device;
import com.jcohy.scis.repository.DeviceRepository;
import com.jcohy.scis.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements DeviceService
{
    @Autowired
    private DeviceRepository deviceRepository;


    @Override
    public List<Device> getDeviceList()
    {
        return deviceRepository.getDeviceList();
    }

    @Override
    public List<Device> getBorrowableDeviceList()
    {
        return deviceRepository.getBorrowableDeviceList();
    }

    @Override
    public List<Device> getDeviceListByOwnerId(Integer ownerId)
    {
        return deviceRepository.getDeviceListByOwnerId(ownerId);
    }

    @Override
    public int createDevice(String name, String category, Integer ownerId, String owner,String state, String condition, String borrower) throws Exception
    {
        return deviceRepository.createDevice(name,category,ownerId,owner,state,condition,borrower);
    }

    @Override
    public void delete(Integer id)
    {
        if(id == null){
            throw new ServiceException("主键不能为空");
        }
        deviceRepository.deleteById(id);
    }

    @Override
    public List<Map<String, Object>> getDeviceBorrowList(Integer deviceId)
    {
        return deviceRepository.getDeviceBorrowList(deviceId);
    }

    @Override
    public List<Map<String, Object>> getDeviceListByBorrowerId(Integer borrowerId)
    {
        return deviceRepository.getDeviceListByBorrowerId(borrowerId);
    }

    @Override
    public int createBorrowDeviceRequest(Integer deviceId, Integer borrowerId, String state, Date borrowDate, Date returnDate, String condition, String detail) throws Exception
    {
        deviceRepository.createBorrowDeviceRequest(deviceId,borrowerId,state,borrowDate,returnDate,condition,detail);
        return deviceRepository.updateDevice(deviceId,borrowerId,state);
    }

    @Override
    public int updateDeviceBorrowRecord(Integer id, Integer deviceId, String state, Date returnDate, String condition, String detail)
    {
        deviceRepository.updateDeviceBorrowRecord(id,state,returnDate,condition,detail);
        if(state.equals("已归还")){
            state = "可借用";
            return deviceRepository.updateDevice(deviceId,state,condition,"");
        }
        return deviceRepository.updateDevice(deviceId,state,condition);
    }

    @Override
    public int updateDeviceBorrowRecord(Integer id, Integer deviceId, String state)
    {
        deviceRepository.updateDeviceBorrowRecord(id,state);
        return deviceRepository.updateDevice(deviceId,state);
    }


}

package edu.hust.set.lora_app.services;

import java.util.List;
import java.util.Optional;

import edu.hust.set.lora_app.models.Device;

/**
 * PacketService
 */
public interface DeviceService {

    List<Device> getAllDevice();  

    void saveDevice(Device device);  
  
    void deleteDevice(String device_id);  
  
    Optional<Device> findDeviceById(String device_id);  
}
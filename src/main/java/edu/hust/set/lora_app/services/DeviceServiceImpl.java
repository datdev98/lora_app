package edu.hust.set.lora_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hust.set.lora_app.models.Device;
import edu.hust.set.lora_app.repositories.DeviceRepository;
import edu.hust.set.lora_app.services.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public List<Device> getAllDevice() {
        return (List<Device>) deviceRepository.findAll();
    }

    @Override
    public void saveDevice(Device device) {
        deviceRepository.save(device);
    }

    @Override
    public void deleteDevice(String device_id) {
        deviceRepository.deleteById(device_id);
    }

    @Override
    public Optional<Device> findDeviceById(String device_id) {
        return deviceRepository.findById(device_id);
    }
    
}
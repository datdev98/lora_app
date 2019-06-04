package edu.hust.set.lora_app.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.hust.set.lora_app.models.Device;

public interface DeviceRepository extends CrudRepository<Device, String> {
    
}
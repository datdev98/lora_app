package edu.hust.set.lora_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import edu.hust.set.lora_app.models.Device;
import edu.hust.set.lora_app.services.DeviceService;

@Controller
public class DeviceController {
    DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/devices")
    public String greeting(Model model) {
        List<Device> devices = this.deviceService.getAllDevice();
        model.addAttribute("devices", devices);
        return "devices";
    }
    
}
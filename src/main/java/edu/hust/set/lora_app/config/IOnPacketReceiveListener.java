package edu.hust.set.lora_app.config;

import edu.hust.set.lora_app.models.Device;

public interface IOnPacketReceiveListener {
    void OnPacketReceiveEvent(Device device);
}
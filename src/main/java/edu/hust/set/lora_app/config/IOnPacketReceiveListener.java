package edu.hust.set.lora_app.config;

import edu.hust.set.lora_app.models.Packet;

/**
 * IOnPacketReceiveListener
 */
public interface IOnPacketReceiveListener {
    void OnPacketReceiveEvent(Packet packet);
}
package edu.hust.set.lora_app.services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.function.BiConsumer;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thethingsnetwork.data.common.Connection;
import org.thethingsnetwork.data.common.messages.DataMessage;
import org.thethingsnetwork.data.common.messages.UplinkMessage;
import org.thethingsnetwork.data.mqtt.Client;

import edu.hust.set.lora_app.models.Packet;
import edu.hust.set.lora_app.repositories.PacketRepository;

@Service
public class TTNMqttService {
    private final String region = "asia-se";
    private final String appId = "lora_app_project2";
    private final String accessKey = "ttn-account-v2.kkkZKlOCkrt5bjauBaP7e8dhJmKkKfMUZa87bc_doEY";
    private Client client;
    private final PacketRepository packetRepository;

    private BiConsumer<String, DataMessage> onPayloadHandler = new BiConsumer<String, DataMessage>() {

        @Override
        public void accept(String _devId, DataMessage _data) {
            if (_data instanceof UplinkMessage) {
                UplinkMessage msg = (UplinkMessage) _data;
                String device_id = _devId;
                Double amount = Double.parseDouble(msg.getPayloadFields().get("amount").toString());
                Packet packet = new Packet();
                packet.setAmount(amount);
                packet.setDevice_id(device_id);
                packetRepository.save(packet);
            }
        }
    };

    @Autowired
    public TTNMqttService(PacketRepository packetRepository) throws MqttException, Exception {
        this.packetRepository = packetRepository;
    }

    public void Connect() throws MqttException, Exception {
        client = new Client(this.region, this.appId, this.accessKey);
        client.onError((Throwable _error) -> System.err.println("error: " + _error.getMessage()));
        client.onConnected((Connection _client) -> System.out.println("connected !"));
        client.onMessage(onPayloadHandler);
        client.start();  
    }

    public void Disconnect() throws MqttException {
        client.endNow();
    }
}
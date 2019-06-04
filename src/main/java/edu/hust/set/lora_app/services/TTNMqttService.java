package edu.hust.set.lora_app.services;

import java.io.IOException;
import java.util.Optional;
import java.util.function.BiConsumer;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thethingsnetwork.data.common.Connection;
import org.thethingsnetwork.data.common.messages.DataMessage;
import org.thethingsnetwork.data.common.messages.UplinkMessage;
import org.thethingsnetwork.data.mqtt.Client;

import edu.hust.set.lora_app.models.Device;
import edu.hust.set.lora_app.repositories.DeviceRepository;

@Service
public class TTNMqttService {
    private final String region = "asia-se";
    private final String appId = "lora_app_project2";
    private final String accessKey = "ttn-account-v2.kkkZKlOCkrt5bjauBaP7e8dhJmKkKfMUZa87bc_doEY";
    private Client client;
    private final DeviceRepository packetRepository;

    private BiConsumer<String, DataMessage> onPayloadHandler = new BiConsumer<String, DataMessage>() {

        @Override
        public void accept(String _devId, DataMessage _data) {
            if (_data instanceof UplinkMessage) {
                UplinkMessage msg = (UplinkMessage) _data;
                String device_id = _devId;
                Double temperature = Double.parseDouble(msg.getPayloadFields().get("temperature").toString());
                Double humidity = Double.parseDouble(msg.getPayloadFields().get("humidity").toString());
                Double light = Double.parseDouble(msg.getPayloadFields().get("light").toString());
                String time = msg.getMetadata().getTime();
                Device device;
                Optional<Device> result = packetRepository.findById(device_id);
                if (result.isPresent()) {
                    device = result.get();
                } else {
                    device = new Device();
                }
                device.setDevice_id(device_id);
                device.setTemperature(temperature);
                device.setHumidity(humidity);
                device.setDateTime(time);
                device.setLight(light);
                try {
                    device.setHasHumman(this.checkHuman(humidity, light, temperature));
                } catch (Exception e) {
                }
                packetRepository.save(device);
            }
        }

        private int checkHuman(Double humidity, Double light, Double temperature) throws Exception {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("python3 service_python/run_model.py " + humidity + " " + light + " " + temperature);
            int code =  pr.waitFor();
            if (code == -1) {
                throw new Exception();
            }
            return code;
        }
    };

    @Autowired
    public TTNMqttService(DeviceRepository packetRepository) throws MqttException, Exception {
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
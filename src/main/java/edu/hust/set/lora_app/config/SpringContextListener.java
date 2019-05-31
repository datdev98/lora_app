package edu.hust.set.lora_app.config;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import edu.hust.set.lora_app.services.TTNMqttService;

@Component
public class SpringContextListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    TTNMqttService ttnMQttService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            ttnMQttService.Connect();
        } catch (MqttException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
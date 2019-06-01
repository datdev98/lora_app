package edu.hust.set.lora_app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Packet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String device_id;

    private String dateTime;

    private Double temperature;

    private Double humidity;

    private Double light;

    private Boolean hasHumman;

    public Double getLight() {
        return light;
    }

    public Boolean getHasHumman() {
        return hasHumman;
    }

    public void setHasHumman(Boolean hasHumman) {
        this.hasHumman = hasHumman;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setLight(Double light) {
        this.light = light;
    }

    public Double getHumidity() {
 
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

}
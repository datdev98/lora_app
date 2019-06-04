#include <dht11.h>
#include <Wire.h>
#include <BH1750.h>
#define DHT11PIN 4

dht11 DHT11;
BH1750 lightMeter;
void setup()
{
  Serial.begin(9600);
  Wire.begin();
  lightMeter.begin();
  Serial.print("AT+JOIN\n");
  delay(2000);
}

String padd_zero(String string, int length) {
  String zero = "";
  int n_zero = length - string.length();
  if (n_zero > 0) {
    for (int i = 0; i < n_zero; i++) {
      zero += "0";
    }
  }
  return zero + string;
}

void loop()
{
  Serial.println();
  int chk = DHT11.read(DHT11PIN);
  float lux = lightMeter.readLightLevel();
  int i_lux = lux * 100;
  String humidity = String(DHT11.humidity, HEX);
  String temperature = String(DHT11.temperature, HEX);
  String light = String(i_lux, HEX);
  String package = "AT+SENDB=2:" + padd_zero(humidity, 2) + padd_zero(temperature, 2) + padd_zero(light, 4) + '\n';
//  Serial.print("Humidity: " + (String)DHT11.humidity + "\n");
//  Serial.print("Temperature: " + (String)DHT11.temperature + "\n");
//  Serial.print("Light: " + (String)lux + "\n");
  Serial.print(package);
  delay(60000);
}

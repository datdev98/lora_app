#include <dht11.h>
#include <Wire.h>
#include <BH1750.h>
#include <SoftwareSerial.h>
#define DHT11PIN 4

dht11 DHT11;
BH1750 lightMeter;
SoftwareSerial mySerial(0, 1);
void setup()
{
  Serial.begin(9600);
  mySerial.begin(9600);
  Wire.begin();
  lightMeter.begin();
  Serial.print("AT+JOIN\n");
  pinMode(A0, OUTPUT);
}

void loop()
{
  Serial.println();
  int chk = DHT11.read(DHT11PIN);
//  Serial.print("Humidity (%): ");
//  Serial.println((float)DHT11.humidity, 2);
//  Serial.print("Temperature (C): ");
//  Serial.println((float)DHT11.temperature, 2);
  float lux = lightMeter.readLightLevel();
//  Serial.print("Light: ");
//  Serial.print(lux);
//  Serial.println(" lx");
  String package = "AT+SEND=2:" + (String)DHT11.humidity + "-" + (String)DHT11.temperature + "-" + (String)lux + '\n';
  Serial.print(package);
//  if (mySerial.available()) {
//    Serial.write(mySerial.read());
//  }
//  if (Serial.available()) {
  mySerial.print(package);
//    mySerial.write(Serial.read());
//  }
  delay(30000);

  

}

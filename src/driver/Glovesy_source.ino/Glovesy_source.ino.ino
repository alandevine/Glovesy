// Basic demo for accelerometer/gyro readings from Adafruit LSM6DS33

#include <Adafruit_LSM6DS33.h>

// For SPI mode, we need a CS pin
#define LSM_CS 10
// For software-SPI mode we need SCK/MOSI/MISO pins
#define LSM_SCK 13
#define LSM_MISO 12
#define LSM_MOSI 11

Adafruit_LSM6DS33 lsm6ds33;
void setup(void) {
  Serial.begin(115200);
  while (!Serial)
    delay(10); // will pause Zero, Leonardo, etc until serial console opens

  if (!lsm6ds33.begin_I2C()) {
    // if (!lsm6ds33.begin_SPI(LSM_CS)) {
    // if (!lsm6ds33.begin_SPI(LSM_CS, LSM_SCK, LSM_MISO, LSM_MOSI)) {
    Serial.println("Failed to find LSM6DS33 chip");
    while (1) {
      delay(10);
    }
  }


  lsm6ds33.configInt1(false, false, true); // accelerometer DRDY on INT1
  lsm6ds33.configInt2(false, true, false); // gyro DRDY on INT2
}

float get_resistance(int ADCflex) {
  float VCC = 3.3;
  int R_DIV = 4700;
  float Vflex = ADCflex * VCC / 1023.0;
  float Rflex = R_DIV * (VCC / Vflex - 1.0);

  return Rflex;
}

void loop() {
  //  /* Get a new normalized sensor event */
  sensors_event_t accel;
  sensors_event_t gyro;
  sensors_event_t temp;
  lsm6ds33.getEvent(&accel, &gyro, &temp);
  
  // Thumb
  Serial.print(get_resistance(analogRead(A4)));
  Serial.print(",");

  // Index Distal
  Serial.print(get_resistance(analogRead(A0)));
  Serial.print(",");

  // Index Proximal
  Serial.print(get_resistance(analogRead(A1)));
  Serial.print(",");

  // Middle Distal
  Serial.print(get_resistance(analogRead(A2)));
  Serial.print(",");

  // Middle Proximal
  Serial.print(get_resistance(analogRead(A3)));
  Serial.print(",");

  // Ring
  Serial.print(get_resistance(analogRead(A5)));
  Serial.print(",");

  // Pinky
  Serial.print(get_resistance(analogRead(9)));
  Serial.print(",");
  Serial.print(accel.acceleration.x);
  Serial.print(",");
  Serial.print(accel.acceleration.y);
  Serial.print(",");
  Serial.print(accel.acceleration.z);
  Serial.print(",");
  Serial.print(gyro.gyro.x);
  Serial.print(",");
  Serial.print(gyro.gyro.y);
  Serial.print(",");
  Serial.print(gyro.gyro.z);
  Serial.println();

  
  delay(1000);
}

# Glovesy
Alan Devine: 17412402 <br>
Sean Moloney: 17477122

## 1. Overview

Glovesy is an Arduino based wearable device which allows the user to interface with their computer, either by using user-defined macros, which will be set up using our program, the Glovesy Configuration Suite, which will allow several gestures to be defined to certain actions within the PC or by allowing the user accurate hand and finger tracking for use in Virtual and Augmented Reality.

Gestures are defined by the user and mapped to some action on their PC. Actions can include entering a combination of keystrokes, opening an application, raising/ lowering system volume, etc. They are executed upon the user repeating their chosen gesture.

## 2. Motivation

## 3. Research

Overall a significant amount of research was carried out before begining the development of Glovesy in order to see if our idea would be possible to implement given our current technical expertise and within a reasonable budget.

### 3.1 Finger Tracking

One of the main components that we carried out research on was how the fingers would be tracked.

While we found a number of low-budget ways to track the user's fingers, such as using potentiometers attached to the fingers using fishing line or string, however using this method means that the glove will be much larger and bulkier than using flex sensors. This method also has the downside of the increased chance of snagging due to the fishing line attached to the fingers.

In the end, we decided to use flex sensors, as while there are expensive versions of them such as [the ones found on Adafruit](https://www.adafruit.com/product/1070), it is possible to buy the materials and construct them for much cheaper. Flex sensors are also very low profile and sit flush along the glove, making it feel more natural and reduces the chances of snagging.

### 3.2 Hand Tracking

When researching methods for tracking the user's hand, while there were a few other options, such as using a HTC Vive Tracker, however this is not only an expensive option, but it also requires at least one HTC Vive Lighthouse for tracker. On top of this, when attached, the tracker is bulky and can easily snag or catch on something due to it's design, especially when using a VR headset, since the user cannot see their surroundings.  Instead, the best option we could find was using an 9DoF IMU, specifically the [Adafruit LSM6DS33 LIS3MDL](https://www.adafruit.com/product/4485), as it was both cheap and small, while also offering accurate accelerometer and gyroscope data.

### 3.3 Board

When looking for a board to use, there were a number of requirements which had to be satisfied for it to function adequately. Those requirements were: 
1. the board must have enough analog pins to read data from each of the flex sensors
2. the board must have i2c compaitibility in order to receive data from the IMU
3. the board must be compact in terms of its form factor in order to reduce the bulk of the glove and make it more comfortable to wear.

While researching said boards, there were a few boards that fit the requirements, such as the [TinyCircuits TinyLilly Mini](https://tinycircuits.com/products/tinylily-mini-processor?_pos=10&_sid=cbfd15ac9&_ss=r), but we decided to use the [Adafruit Feather M0 Bluefruit LE](https://www.adafruit.com/product/2995), since it not only meets all the requirements, but is also bluetooth enable, which would allow us to create a wireless version of the device without needing to purchase new components.

### 3.4 Software

### 3.5 Game Engines

While researching which game engines to create the demo environment in, a number of them were suitable, such as Unreal Engine or Godot, however using unity ended up being the best option, since it is compatible with linux and also allows for easy use of custom devices to interface with the engine.

### 3.6 Competitors 

Upon researching to see if the idea had already been done by a company, or if the idea was even feasable to begin with, we discovered a number of different implementations of the same idea. A very high fidelity solution to our idea can be found in the [Manus VR Primus II](https://www.manus-vr.com/mocap-gloves), however this very high fidelity solution is very costly at a price of €2,499.  Another solution which takes a different approach to the problem is the [Senseglove](https://www.senseglove.com/), which is not only very bulky, being much larger than the user's hand, but is also very expensive, at €2,999. A final example is the [HAPTX gloves](https://haptx.com/) which are once again very bulky, including a backback which must be worn during use.

Another downside to all of the aforementioned competitors is their reliance on existing vr systems such as the [HTC Vive Trackers](https://www.vive.com/eu/accessory/vive-tracker/), or the [Occulus Controllers](https://www.oculus.com/quest-2/accessories/) which not only require the user to have a vr environment set up, but also add bulk to the glove, particularly the Occulus Controllers, alongside being somewhat expensive.

## 4. Design

## 5. Implementation

### 5.1 Flex Sensor
### 5.2 IMU
### 5.3 On-Board Program
### 5.4 Demo Hub
### 5.5 Glovesy Configuration Suite

## 6. Problems and Solutions

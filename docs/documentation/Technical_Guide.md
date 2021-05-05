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

In the end, we decided to use flex sensors, as while there are expensive versions of them, it is possible to buy the materials and construct them for much cheaper. Flex sensors are also very low profile and sit flush along the glove, making it feel more natural and reduces the chances of snagging.

### 3.2 Hand Tracking

When researching methods for tracking the user's hand, while there were a few other options, such as using a HTC Vive Tracker, however this is not only an expensive option, but it also requires at least one HTC Vive Lighthouse for tracker. On top of this, when attached, the tracker is bulky and can easily snag or catch on something due to it's design, especially when using a VR headset, since the user cannot see their surroundings.  Instead, the best option we could find was using an 9DoF IMU, specifically the Adafruit LSM6DS33 LIS3MDL, as it was both cheap and small, while also offering accurate accelerometer and gyroscope data.

### 3.3 Board

### 3.4 Software
### 3.5 Game Engines
### 3.6 Competitors


## 4. Design

## 5. Implementation

### 5.1 Flex Sensor
### 5.2 IMU
### 5.3 On-Board Program
### 5.4 Demo Hub
### 5.5 Glovesy Configuration Suite

## 6. Problems and Solutions

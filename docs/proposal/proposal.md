# School of Computing &mdash; Year 4 Project Proposal Form

## SECTION A

| Project Title:      | Glovesy        |
| ------------------- | -------------- |
| Student 1 Name:     | Alan Devine    |
| Student 1 ID:       | 17412402       |
| Student 2 Name:     | Sean Moloney   |
| Student 2 ID:       | 17477122       |
| Project Supervisor: | David Sinclair |

## SECTION B

### Introduction

Glovesy is a wearable hand tracking device which will allow the user to interface with their PC, using user-defined macros, accurate and immersive hand tracking and grip control in virtual reality and augmented reality, as well general desktop use and improved workflow.

### Outline

In this project we will be creating a wearable device, in the form of a glove, which will track the user's hand and finger movement in order to allow them to interface with their pc, using either gesture controls which are mapped to custom macros, or by allowing the user to have significantly higher hand control in VR or AR games. On top of this, we will be creating a number of demos in order to demonstrate the capabilities and usability of the glove.

### Background

The idea for Glovsey came from a game called Heavy Rain. In this game there is an FBI agent called Norman Jayden who has this device called ARI. ARI is a AR headset and glove used for collecting evidence in the field. There is one particular scene where Norman is waiting for a meeting. He puts on the ARI and we see a wall materialise in front of him and he is cupping a ball. He then proceeds to kill time by throwing this virtual ball against the wall to kill time before the meeting. 

We thought this part of the game was incredibly gimmicky however it got us thinking. To effectively place ourselves in these virtual environments through use of AR or VR, we need an interface to control these environments that is already know to use for the effect to take hold, i.e. our hands.

### Achievements

As stated above, the function of the glove would be to capture data related to the state of both fingers and the overall hand, where the finger data will mainly consist of how bent the finger is. The hand data relates to the position of the hand in a 3D space, i.e. orientation of the palm and acceleration in various directions. This data would then be matched to user defined macros that would achieve a variety of functions on a connected device.

We envision our users as being owners of VR/AR devices, or people who cannot use a standard keyboard and mouse by virtue of their job. An example of this would be presenter who moves around a stage.

### Justification

With the advancement in AR and VR products, an alternative input device is needed. With regards to VR, most VR headsets include some form of controller, typically consisting of two handheld devices with buttons positioned around the device in areas that the user is most likely to rest their fingers. This is effective, but leaves much to be desired. In the case that your avatar was to be holding an object, like a sword, this would be second nature, however once the avatar's hands are free then there is a clearer disconnect between reality and virtual reality.

Similarity there is a issue with AR devices. Although the AR market is less mature than the VR market, we can look on past implementations. Many AR devices rely on speech recognition in order to interact with the device. While this approach works, this is not the most accessible approach, users that suffer from speech impediments and mute users would have no way of interacting with an AR Headset.

We believe that products that are built upon the premise of an extension of reality should have controls to reflect that.

### Programming language(s)

#### C++

C++ will be used to write the "Sketches" for the Arduino and the drivers on both Windows and *nix devices. 

#### Java

Java will be used for creating the desktop application and daemon for matching gestures to macros.

### Programming tools / Tech stack

- Arduino Studio
  - For creating Arduino "Sketches".
- Intellij
  - For all Java development.
- JSON
  - For storing user macros and configuration.
- Bluetooth Low Energy
  - The means in which data will be transferred from the glove to a host machine.

### Hardware

Below are the main hardware components. We have left out some of the other components as they can be interchanged with similar products without a change to the system.

| Name                             | Description                           |
| -------------------------------- | ------------------------------------- |
| Adafruit Feather M0 Bluefruit LE | Arduino board with built in Bluetooth |
| Adafruit LSM6DS33 + LIS3MDL      | 9-DoF IMU                             |

### Learning Challenges

This project will provide a number of learning challenges and opportunities. We will be exposed to many new technologies such as Arduino and transferring data over Bluetooth Low Energy. At a lower level we will have to learn about writing drivers to be able to read the data captured through the glove.

### Breakdown of work

#### Alan Devine

I will be creating a system for recognising hand movements and translating these into actions/ macros that will take place on the connected device. In addition to this, I will also create a companion application that will allow the user to record actions and define function for said actions to trigger.

Time permitting, I will also create interactive demos for use in the project expo in the case that campus reopens.

#### Sean Moloney

I will initally be creating a driver in order to interpret the output from the glove in order for Alan's configuration software to be able to take that output and map it to certain user defined macros.
Once both the configuration software and driver are complete I will be working on a number of demonstrations to show off the capabilities of the glove alongside Alan's own demos.
# School of Computing &mdash; Year 4 Project Proposal Form

> Edit (then commit and push) this document to complete your proposal form.
> Make use of figures / diagrams where appropriate.
>
> Do not rename this file.

## SECTION A

|                     |                |
| ------------------- | -------------- |
| Project Title:      | xxxxxx         |
| Student 1 Name:     | Alan Devine    |
| Student 1 ID:       | 17412402       |
| Student 2 Name:     | Sean Moloney   |
| Student 2 ID:       | 17477122       |
| Project Supervisor: | David Sinclair |

> Ensure that the Supervisor formally agrees to supervise your project; this is only recognised once the
> Supervisor assigns herself/himself via the project Dashboard.
>
> Project proposals without an assigned
> Supervisor will not be accepted for presentation to the Approval Panel.

## SECTION B

> Guidance: This document is expected to be approximately 3 pages in length, but it can exceed this page limit.
> It is also permissible to carry forward content from this proposal to your later documents (e.g. functional
> specification) as appropriate.
>
> Your proposal must include *at least* the following sections.


### Introduction

> Describe the general area covered by the project.

### Outline

> Outline the proposed project.

### Background

> Where did the ideas come from?

The idea for Glovsey came from a game called Heavy Rain. In this game there is an FBI agent called Norman Jayden who has this device called ARI. ARI is a AR headset and glove used for collecting evidence in the field. There is one particular scene where Norman is waiting for a meeting. He puts on the ARI and we see a wall materialise in front of him and he is cupping a ball. He then proceeds to kill time by throwing this virtual ball against the wall to kill time before the meeting. 

We thought this part of the game was incredibly gimmicky however it got us thinking. To effectively place ourselves in these virtual environments through use of AR or VR, we need an interface to control these environments that is already know to use for the effect to take hold, i.e. our hands.

### Achievements

> What functions will the project provide? Who will the users be?

As stated above, the function of the glove would be to capture data related to the state of both fingers and the overall hand, where the finger data will mainly consist of how bent the finger is. The hand data relates to the position of the hand in a 3D space, i.e. orientation of the palm and acceleration in various directions. This data would then be matched to user defined macros that would achieve a variety of functions on a connected device.

We envision our users as being owners of VR/AR devices, or people who cannot use a standard keyboard and mouse by virtue of their job. An example of this would be presenter who moves around a stage.

### Justification

> Why/when/where/how will it be useful?

With the advancement in AR and VR products, an alternative input device is needed. With regards to VR, most VR headsets include some form of controller, typically consisting of two handheld devices with buttons positioned around the device in areas that the user is most likely to rest their fingers. This is effective, but leaves much to be desired. In the case that your avatar was to be holding an object, like a sword, this would be second nature, however once the avatar's hands are free then there is a clearer disconnect between reality and virtual reality.

Similarity there is a issue with AR devices. Although the AR market is less mature than the VR market, we can look on past implementations. Many AR devices rely on speech recognition in order to interact with the device. While this approach works, this is not the most accessible approach, users that suffer from speech impediments and mute users would have no way of interacting with an AR Headset.

We believe that products that are built upon the premise of an extension of reality should have controls to reflect that.

### Programming language(s)

> List the proposed language(s) to be used.

- C++
- Java

### Programming tools / Tech stack

> Describe the compiler, database, web server, etc., and any other software tools you plan to use.

- Arduino Studio
- Intellij
- JSON

### Hardware

> Describe any non-standard hardware components which will be required.

| Name                             | Description                           |
| -------------------------------- | ------------------------------------- |
| Adafruit Feather M0 Bluefruit LE | Arduino board with built in Bluetooth |
| Adafruit LSM6DS33 + LIS3MDL      | 9-DoF IMU                             |



### Learning Challenges

> List the main new things (technologies, languages, tools, etc) that you will have to learn. 
> 

This project will provide a number of learning challenges and opportunities. We will be exposed to many new technologies such as Arduino and transferring data over Bluetooth Low Energy. At a lower level we will have to learn about writing drivers to be able to read the data captured through the glove.

### Breakdown of work

> Clearly identify who will undertake which parts of the project.
>
> It must be clear from the explanation of this breakdown of work both that each student is responsible for
> separate, clearly-defined tasks, and that those responsibilities substantially cover all of the work required
> for the project.

#### Alan Devine
I will be creating a system for recognising hand movements and translating these into actions/ macros that will take place on the connected device. In addition to this, I will also create a companion application that will allow the user to record actions and define function for said actions to trigger.

Time permitting, I will also create interactive demos for use in the project expo in the case that campus reopens.

#### Sean Moloney

I will initally be creating a driver in order to interpret the output from the glove in order for Alan's configuration software to be able to take that output and map it to certain user defined macros.

Once both the configuration software and driver are complete I will be working on a number of demonstrations to show off the capabilities of the glove alongside Alan's own demos.
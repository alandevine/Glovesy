# Glovesy
Alan Devine: 17412402 <br>
Sean Moloney: 17477122

## 1. Overview

Glovesy is an Arduino based wearable device which allows the user to interface with their computer, either by using user-defined macros, which will be set up using our program, the Glovesy Configuration Suite, which will allow several gestures to be defined to certain actions within the PC or by allowing the user accurate hand and finger tracking for use in Virtual and Augmented Reality.

Gestures are defined by the user and mapped to some action on their PC. Actions can include entering a combination of keystrokes, opening an application, raising/ lowering system volume, etc. They are executed upon the user repeating their chosen gesture.

## 2. Motivation

The idea for glovesy came from our analysis of the input devices used in VR/ AR applications. Typically in the case of VR applications, standard input devices mostly come in the form of controllers held in each hand with an array of buttons and some mechanism to provide tracking on the top. AR headsets, generally rely on voice-activated services such, or, the use of a smartphone to interact with the device. In both applications, we agreed that using a glove based input device would provide a more immersive VR gaming experience, and more a more intuitive way of interacting with AR content.

Furthermore, we saw a great learning opportunity with this project. We would be exposed to programming microcontrollers, GUI development and game development to name a few. We also would have the opportunity to make use of some of the skills we developed while undergoing this degree, namely applying linear algebra to a real project.

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

### 3.4 Game Engines

During research into which game engine to use as the basis for the demonstrations, there were a number of valid options. One of said options being the [Godot engine](https://godotengine.org/) as it is a relatively easy engine to develop for, however, due to the limitations of the engine, it was decided that another engine would be better suited, especially due to difficulty when getting input from non-standard controllers. Another favourable option to use was [Unreal Engine](https://www.unrealengine.com/) as it uses C++ which is a language we both have experience in. However, this option was ruled out due to compatibility issues when developing on linux systems. As such we decided to use [Unity](https://unity.com/) as it is a cross-platform game engine, as well as allowing non-standard input devices to be used with relative ease. This, though, was not an ideal scenario as we have little experience using Unity as well as little experience programming in C#.

### 3.5 Language for implementing AR Suite

Choosing a language for this aspect of the project was rather straightforward. It had to meet certain requirements. The first requirement was for the language to be statically typed, this is mostly down to personal preference as I have found dynamically typed languages to be cumbersome when working with larger codebases. The next requirement was for the language to be platform agnostic. With all that considered, we settled on Java.

### 3.6 Build Tool

The choice in build tools came down to two candidates, Maven and Gradle. Having used both in the past, I settled on Gradle as it is typically quick to set up and modify as well as having great support in my Java ide of choice. It also provides some excellent features such as automatically generating a test result site on each build of the project.

### 3.7 GUI Library

Having settled on Java as the programming language to be used for the AR aspect of this project, we needed to choose a library for GUI development. Requirements for a GUI library mainly came down to the availability of documentation and the ability to create 3D models. While researching libraries, we discovered that Javafx satisfies both requirements.

### 3.8 Competitors 

Upon researching to see if the idea had already been done by a company, or if the idea was even feasable to begin with, we discovered a number of different implementations of the same idea. A very high fidelity solution to our idea can be found in the [Manus VR Primus II](https://www.manus-vr.com/mocap-gloves), however this very high fidelity solution is very costly at a price of €2,499.  Another solution which takes a different approach to the problem is the [Senseglove](https://www.senseglove.com/), which is not only very bulky, being much larger than the user's hand, but is also very expensive, at €2,999. A final example is the [HAPTX gloves](https://haptx.com/) which are once again very bulky, including a backback which must be worn during use.

Another downside to all of the aforementioned competitors is their reliance on existing vr systems such as the [HTC Vive Trackers](https://www.vive.com/eu/accessory/vive-tracker/), or the [Occulus Controllers](https://www.oculus.com/quest-2/accessories/) which not only require the user to have a vr environment set up, but also add bulk to the glove, particularly the Occulus Controllers, alongside being somewhat expensive.

## 4. Design

## 5. Implementation

### 5.1 Flex Sensor
### 5.2 IMU
### 5.3 On-Board Program
### 5.4 Demo Hub
### Serial Monitoring

Serial monitoring was achieved using the `JSerialComm` library. This required two classes.

#### SerialPortListener

This object implements the `SerialPortMessageListener` which is an interface which allows for the capture of  byte-delimited input streams. Below we define the delimiter as `0x0A` which corresponds to the newline character. When a new line character is detected, the `serialEvent()` method is called, which splits the incoming data and updates the `GloveState` singleton

```Java
package OSHandler;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

public final class SerialPortListener implements SerialPortMessageListener {

    private final GloveState gloveState;
    private boolean captureEvents = false;

    public SerialPortListener(GloveState gloveState) { this.gloveState = gloveState; }

    @Override
    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }

    @Override
    public byte[] getMessageDelimiter() { return new byte[] { (byte) 0x0A }; }

    @Override
    public boolean delimiterIndicatesEndOfMessage() { return true; }

    @Override
    public void serialEvent(SerialPortEvent event) {
        byte[] receivedData = event.getReceivedData();
        String input = new String(receivedData).replace("\n", "");
        this.gloveState.updateState(input.split(","));
    }
}

```



#### SerialComms

SerialComms is a runnable object which manages the connection to Arduino. If found, the a `SerialPortListener` object will be added to the `arduinoPort` , otherwise an exception will be raised.

```Java
String deviceName = "Feather M0";

SerialPort arduinoPort = null;
for (SerialPort commPort : SerialPort.getCommPorts()) {
    if (commPort.getDescriptivePortName().contains(deviceName)) {
        arduinoPort = commPort;
        commPort.openPort();
        break;
    }
}

if (arduinoPort == null)
    throw new FileNotFoundException(String.format("Could not find device \"%s\".", deviceName));

arduinoPort.addDataListener(serialPortListener);

```

### Main GUI

Since we are making use of JavaFX, each window is broken down into two components, a controller and a corresponding FXML file. Similarly to HTML, the FXML file outlines the layout of the window with all functionality being defined in the controller. For example, below is the definition of a button used to launch the configuration window. We can several fields such as the position of the button which is denoted by the `layoutX` and `layoutY` parameters. Additionally we can see `fx:id` and `onAction` parameters.

```xml
<Button fx:id="configurationButton"
        layoutX="622.0"
        layoutY="575.0"
        mnemonicParsing="false"
        onAction="#reconfigureGlove"
        text="Configure Glove" />
```

Below is the corresponding code in the Controller class. As you can see both of the below snippets are referenced in the above FXML, hence they are attributed with the `@FXML` decorator. 

```java
@FXML public Button configurationButton;
```

```java
@FXML
public void reconfigureGlove() throws IOException {
    this.serialComms.startCapture();

    URL url =  new File("src/main/resources/configurationWindow.fxml").toURI().toURL();
    Parent root = FXMLLoader.load(url);
    Stage stage = new Stage();
    stage.setScene(new Scene(root, 500, 500));
    stage.setAlwaysOnTop(true);
    stage.showAndWait();

    this.serialComms.stopCapture();

    Document maxValues = stateHandler.findMax();
    Document minValues = stateHandler.findMin();

    GloveConfiguration gloveConfiguration = new GloveConfiguration();
    gloveConfiguration.setMinValues(minValues);
    gloveConfiguration.setMaxValues(maxValues);

    gloveConfigurationHandler.updateAll(gloveConfiguration);
    populateGloveConfig();
}
```

### Database

While MongoDB is a flexible database, we wanted it to enforce some standard when adding data to it. This was achieved by breaking up each type of data into two separate classes, a data object and its associated handler. Each handler is an implementation of the `DBHandler` interface. 

```Java
interface DBHandler {

    MongoCollection<Document> collection = null;

    void addEntry(Document doc);

    List<Document> findAllEntries();
    Document findEntry(String query) throws NoSuchElementException, AccessException;
    Boolean containsEntry(Document query);
    void deleteEntry(String query);
}
```

Take the following example of the Application data object and the Application Handler. Below we have the implementation of the `Application` class. It is a simple class which mearly acts to store data and provide some assurances regarding if the supplied paths are valid.

```java

public class Application {

    private String name;
    private String path;

    public Application(Document doc) throws FileNotFoundException, AccessException {
        this.name = (String) doc.get("name");
        setPath((String) doc.get("path"));
    }

    public Application(String name, String path) throws FileNotFoundException, AccessException {
        this.name = name;
        setPath(path);
    }

    public Application(String name) { this.name = name; }

    public String getName() { return this.name; }

    public String getPath() { return this.path; }

    public void setName(String name) { this.name = name; }

    public void setPath(String path) throws FileNotFoundException, AccessException
    {
        if (!verifyPath(path))
            throw new FileNotFoundException(String.format("The file \"%s\" does not exist", path));
        if (!verifyExecutable(path))
            throw new AccessException(String.format("The file \"%s\" is not an executable.", path));
        this.path = path;
    }

    public static boolean verifyPath(String path) {
        File file = new File(path);
        return file.exists();
    }

    public static boolean verifyExecutable(String path) {
        File file = new File(path);
        return file.canExecute();
    }
}


```

Here we have the corrisponding interface with the MongoDB database. The benifits of this implementation is that we can be enforce rules on what data is entered into the database, as well as provide methods for commonly used operations.

```java
public class ApplicationHandler implements DBHandler {
    private final MongoCollection<Document> collection;

    public ApplicationHandler(String url, String collectionName) {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(url));
        MongoDatabase database = mongoClient.getDatabase(collectionName);
        collection = database.getCollection("applications");
    }

    @Override
    public void addEntry(Document query) {
        if (!isValidFile(query))
            throw new IllegalArgumentException("");

        collection.insertOne(query);
    }

    @Override
    public List<Document> findAllEntries() {
        List<Document> apps = new ArrayList<>();
        FindIterable<Document> iterable = collection.find();
        for (Document document : iterable) apps.add(document);
        return apps;
    }

    @Override
    public Document findEntry(String query) throws NoSuchElementException, AccessException {
        FindIterable<Document> docs = collection.find(Filters.eq("name", query));
        Iterator<Document> doc = docs.iterator();
        return doc.next();
    }

    @Override
    public Boolean containsEntry(Document query) {
        try {
            this.findEntry(query.getString("name"));
            return true;
        } catch (NoSuchElementException | AccessException ex) {
            return false;
        }
    }

    @Override
    public void deleteEntry(String name) {
        collection.deleteOne(Filters.eq("name", name));
    }
    ...
}

```

### Macro Execution

Macro Execution was achieved using the `Robot` object in the AWT package which came as part of the standard library. Keyboard Macros defined by the user are stored with as a squence of the state of the key, i.e. presses or released, followed by the keycode defined by Java AWT. Below is a macro which when executed, changes window using alt + tab.

```
press,18
press,9
release,9
release,18
```

This is achieved by the following code. We start off by parsing the key sequence. If the key sequence is valid, each step is executed using the aforementioned `Robot` object. A key sequence is considered invalid if an action is not equal to "press" or "release", or if a pressed key is not released.

```java
public void executeKeySequence(String[] keySequence) {
    String key;
    String action;

    if (!parseKeySequence(keySequence))
        throw new InvalidParameterException("The provided key sequence failed to parse successfully");

    for (String line : keySequence) {
        action = line.split(",")[0];
        key = line.split(",")[1];

        if (action.equals("press"))
            robot.keyPress(Integer.parseInt(key));
        else
            robot.keyRelease(Integer.parseInt(key));
    }
}

public boolean parseKeySequence(String[] keySequence) {
    ArrayList<String> keys = new ArrayList<>();
    String key;
    String action;

    for (String line : keySequence) {
        action = line.split(",")[0];
        key = line.split(",")[1];

        if (!action.equals("press") && !action.equals("release"))
            return false;

        if (action.equals("press")) {
            keys.add(key);
            continue;
        }

        if (keys.contains(key)) {
            keys.remove(key);
        }
    }

    return keys.size() == 0;
}
```


## 6. Problems and Solutions
### Reading Serial Data worked in a main function, but not when integrated.

When implementing the serial data, I worked in a main function within `SerialComms.java`. Once it was working standalone, I tried to integrate it with the rest of the system only to find that it would read once, then stop altogether. After a bit of research it came to my attention while the library we used for reading serial data, `JSerialComm`, did create and start a thread, the thread would join after one serial event. The solution to this was to make `SerialComms.java` an implementation of the Runnable interface.

### Z-Axis Joint Contraction

When Implementing joint contraction, initially, there was an issue were fingers would not rotate on the Z-Axis. This is obviously a huge issue as for the most part fingers only Z-Axis, or, the axis normal to palm of the hand. The issue stemmed from JavaFX3D having two ways of moving objects on screen, setLayout and setTranslate. Layout is derived from the strictly GUI side of the package. This was fixed by changing Layout to Translate.

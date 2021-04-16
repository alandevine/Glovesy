using System.Collections;
using System.Collections.Generic;
using System.IO.Ports;
using UnityEngine;

public class HandTracking : MonoBehaviour
{
    // Assign stream to serial port associated with glovesy
    SerialPort stream = new SerialPort("ttyACM0", 115200);

    // Start is called before the first frame update
    void Start() {
        // Open the serial stream
        stream.Open();
    }

    // Update is called once per frame
    void Update() {

        // Read line from Serial Stream
        string value = stream.ReadLine();

        // Split Serial data into individual part
        string[] SerialData = value.Split(',');

        Debug.Log(SerialData);
    }
}

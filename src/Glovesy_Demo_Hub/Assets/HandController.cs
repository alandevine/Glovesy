using System.Collections.Generic;
using System.Collections;
using System.IO;
using System.Threading;
using UnityEngine;

public class HandController : MonoBehaviour
{
    StreamReader reader;
    int LOADED;
    string path = "/dev/ttyACM0";
    string GlovesyData;
    float[] ParsedData;
    
    // Start is called before the first frame update
    void Start() {
        init_serial(path);
        InvokeRepeating("ReadSerial",1f,1f);
    }

    void init_serial(string path) {
        try {
            reader = new StreamReader(path);
            LOADED = 1;
            return;
        } catch (FileNotFoundException e) {
            return;
        }
        
    }

    void ReadSerial() {
        Debug.Log(reader.Peek());
        GlovesyData = reader.ReadLine();
        return;
    }

    // Update is called once per frame
    void Update() {
    }
}

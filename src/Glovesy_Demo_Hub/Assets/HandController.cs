using System.Collections.Generic;
using System.Collections;
using System.IO;
using System.Threading;
using UnityEngine;

public class HandController : MonoBehaviour
{
    StreamReader reader;
    string path = "/dev/ttyACM0";
    string GlovesyData;
    float[] ParsedData = {0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f};
   
    // Start is called before the first frame update
    void Start() {
        init_serial(path);
        InvokeRepeating("ReadSerial",1f,1f);
    }

    void init_serial(string path) {
        try {
            reader = new StreamReader(path);
            return;
        } catch (FileNotFoundException e) {
            return;
        }
        
    }

    void ReadSerial() {
        GlovesyData = reader.ReadLine();
        if (GlovesyData.Length > 0) {
            string[] tmp = GlovesyData.Split(',');
            for (int i=0; i < tmp.Length; i++) {
                float toFloat = float.Parse(tmp[i]);
                ParsedData[i] = toFloat;
            }
        }
        return;
    }

    // Update is called once per frame
    void Update() {
    }
}

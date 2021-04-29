using System.Collections.Generic;
using System.Collections;
using System.IO;
using System.Threading;
using UnityEngine.UI;
using UnityEngine;

public class HandController : MonoBehaviour {
    StreamReader reader;
    string path = "/dev/ttyACM0";
    string GlovesyData;
    float[] ParsedData = {0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f};
    float[] Min = {-1.0f,-1.0f,-1.0f,-1.0f,-1.0f,-1.0f,-1.0f};
    float[] Max = {-1.0f,-1.0f,-1.0f,-1.0f,-1.0f,-1.0f,-1.0f};
    float[] NormalizedValues = {0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f};
    int CALIBRATED = 0;
    float timer;
  
    // Start is called before the first frame update
    void Start() {
        init_serial(path);
        InvokeRepeating("ReadSerial",1f,1f);
        //StartCoroutine(GetMinMax());
    }

    void init_serial(string path) {
        try {
            reader = new StreamReader(path);
            return;
        } catch (FileNotFoundException e) {
            return;
        }
        
    }

    IEnumerator GetMinMax() {
        Text instruction = GameObject.Find("Instruction").GetComponent<Text>();
        Text CountDown = GameObject.Find("Countdown").GetComponent<Text>();
        if (timer < 5.0f) {
            instruction.text = "Open your hand as much as possible";
            for (int i=0; i < 7; i++) {
                if (Min[i] == -1.0f || ParsedData[i] < Min[i]) {
                    Min[i] = ParsedData[i];
                }
            }
        } else if (timer < 10.0f) {
            instruction.text = "Now make a fist as tightly as possible";
            for (int i=0; i < 7; i++) {
                if (Max[i] == -1.0f || ParsedData[i] > Max[i]) {
                    Max[i] = ParsedData[i];
                }
            }
        } else {
            instruction.text = "";
            CALIBRATED = 1;
        }
        yield return null;
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

    IEnumerator UpdateTimer() {
        timer += Time.deltaTime;
        yield return null;
    }

    // Update is called once per frame
    void Update() {

        if (CALIBRATED == 0) {
            StartCoroutine(GetMinMax());
            StartCoroutine(UpdateTimer());
            Debug.Log(timer);
        }
    }
}

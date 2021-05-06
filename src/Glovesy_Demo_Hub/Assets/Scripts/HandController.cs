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
    public GameObject IndexPivot, IndexMidPivot, IndexTipPivot;
    public GameObject Hand, Thumb, Thumb_Mid, Thumb_Tip, Index, Index_Mid, Index_Tip, Middle, Middle_Mid, Middle_Tip, Ring, Ring_Mid, Ring_Tip, Little, Little_Mid, Little_Tip; 
  
    public GameObject IndexTarget, IndexMidTarget, IndexTipTarget;
    public Vector3 IndexTargetPos, IndexMidTargetPos, IndexTipTargetPos;
    // Start is called before the first frame update
    void Start() {
        init_serial(path);
        // Read Serial Port for glovesy data
        InvokeRepeating("ReadSerial",.0f,.001f);

        // Get reference to different segments of the hand     
        Hand = GameObject.Find("Hand");
        Thumb = GameObject.Find("Thumb");
        Thumb_Mid = GameObject.Find("Thumb Mid");
        Thumb_Tip = GameObject.Find("Thumb Tip");
        Index = GameObject.Find("Index");
        Index_Mid = GameObject.Find("Index Mid");
        Index_Tip = GameObject.Find("Index Tip");
        Middle = GameObject.Find("Middle");
        Middle_Mid = GameObject.Find("Middle Mid");
        Middle_Tip = GameObject.Find("Middle Tip");
        Ring = GameObject.Find("Ring");
        Ring_Mid = GameObject.Find("Ring Mid");
        Ring_Tip = GameObject.Find("Ring Tip");
        Little = GameObject.Find("Little");
        Little_Mid = GameObject.Find("Little Mid");
        Little_Tip = GameObject.Find("Little Tip");

        // Add pivot points to simulate the knuckles
        IndexPivot = GameObject.Find("Index Pivot");
        IndexMidPivot = GameObject.Find("Index Mid Pivot");
        IndexTipPivot = GameObject.Find("Index Tip Pivot");

        // Create a Target object for each finger segment to point towards
        IndexTarget = GameObject.CreatePrimitive(PrimitiveType.Sphere);
        IndexTarget.transform.position = Index.transform.position + new Vector3(1f, 0f, 0f);
        IndexTarget.transform.localScale = new Vector3(.1f,.1f,.1f);
        IndexTarget.GetComponent<Renderer>().enabled = false;
        IndexTarget.name = "IndexTarget";

        IndexMidTarget = GameObject.CreatePrimitive(PrimitiveType.Sphere);
        IndexMidTarget.transform.position = Index_Mid.transform.position + new Vector3(1f,0f,0f);
        IndexMidTarget.transform.localScale = new Vector3(.1f,.1f,.1f);
        //IndexMidTarget.GetComponent<Renderer>().enabled = false;
        IndexMidTarget.name = "IndexMidTarget";
        IndexMidTarget.transform.parent = IndexTarget.transform;

        IndexTipTarget = GameObject.CreatePrimitive(PrimitiveType.Sphere);
        IndexTipTarget.transform.position = Index_Tip.transform.position + new Vector3(1f,0f,0f);
        IndexTipTarget.transform.localScale = new Vector3(.1f,.1f,.1f);
        IndexTipTarget.GetComponent<Renderer>().enabled = false;
        IndexTipTarget.name = "IndexTipTarget";
        IndexTipTarget.transform.parent = IndexMidTarget.transform;

        // Record starting position of each target
        IndexTargetPos = IndexTarget.transform.position;
        IndexMidTargetPos = IndexMidTarget.transform.position;
        IndexTipTargetPos = IndexTipTarget.transform.position;
        
    }

    void init_serial(string path) {
        try {
            reader = new StreamReader(path);
            return;
        } catch (FileNotFoundException e) {
            Debug.Log(e);
            return;
        }
    }

    IEnumerator GetMinMax() {
        Text instruction = GameObject.Find("Instruction").GetComponent<Text>();
        Text CountDown = GameObject.Find("Countdown").GetComponent<Text>();
        if (timer < 5.0f) {
            instruction.text = "Open your hand as much as possible";
            for (int i=0; i < 7; i++) {
                if (Max[i] == -1.0f || ParsedData[i] > Max[i]) {
                    Max[i] = ParsedData[i];
                }
            }
        } else if (timer < 10.0f) {
            instruction.text = "Now make a fist as tightly as possible";
            for (int i=0; i < 7; i++) {
                if (Min[i] == -1.0f || ParsedData[i] < Min[i]) {
                    Min[i] = ParsedData[i];
                }
            }
        } else {
            instruction.text = "";
            CALIBRATED = 1;
        }
        yield return null;
    }

    void ReadSerial() {
        try {
            GlovesyData = reader.ReadLine();
            if (GlovesyData.Length > 0) {
                string[] tmp = GlovesyData.Split(',');
                for (int i=0; i < 13; i++) {
                    float toFloat = float.Parse(tmp[i]);
                    ParsedData[i] = toFloat;
                }
            }
            return;
        } catch {
            return;
        }
    }

    IEnumerator UpdateTimer() {
        timer += Time.deltaTime;
        yield return null;
    }

    void Normalize() {
        for (int i=0; i < 7; i++) {
            float Normalized = (ParsedData[i] - Min[i]) / (Max[i] - Min[i]);
            if (Normalized > 1.0f) {
                Normalized = 1.0f;
            } else if (Normalized < 0.0f) {
                Normalized = 0.0f;
            }
            NormalizedValues[i] = Normalized;
        }
        return;
    }    

    // Update is called once per frame
    void Update() {

        if (CALIBRATED == 0) {
            StartCoroutine(GetMinMax());
            StartCoroutine(UpdateTimer());
            Debug.Log(timer);
        } else {
            Normalize();

            // Set Target Positions based on data returned from flex sensors
            IndexTarget.transform.position = IndexTargetPos + new Vector3(-1f+NormalizedValues[2],-1f+NormalizedValues[2],0f);

            IndexMidTargetPos = IndexTarget.transform.localPosition + new Vector3(1f,0f,0f);
            IndexTipTargetPos = IndexMidTarget.transform.localPosition + new Vector3(1f,0f,0f);

            IndexMidTarget.transform.localPosition = IndexMidTargetPos + new Vector3(-1f+NormalizedValues[1],-1f+NormalizedValues[1],0f);
            IndexTipTarget.transform.localPosition = IndexTipTargetPos + new Vector3(-1f+NormalizedValues[1],-1f+NormalizedValues[1],0f);
            //IndexMidTarget.transform.position = IndexTarget.transform.position + new Vector3(-1f+NormalizedValues[1],-1f+NormalizedValues[1],0f);
            //IndexTipTarget.transform.position = Index_Mid.transform.position + new Vector3(-1f+NormalizedValues[1],-1f+NormalizedValues[1],0f);

            IndexPivot.transform.LookAt(IndexTarget.transform.position, new Vector3(0f,1.0f,0f));
            IndexMidPivot.transform.LookAt(IndexMidTarget.transform.position, new Vector3(0f,1.0f,0f));

        }
    }
}

using UnityEditor;
using UnityEngine.InputSystem;
using UnityEngine.InputSystem.Controls;
using UnityEngine.InputSystem.Layouts;
using UnityEngine.InputSystem.LowLevel;
using UnityEngine.InputSystem.Utilities;

public struct GlovesyState : IInputStateTypeInfo {
    public FourCC format => new FourCC('M','Y','D','V');

    [InputControl(name="handPostion", layout="Vector3")]
    [InputControl(name="handPosition/x")]
    public float x;
    [InputControl(name="handPosition/y", offset = 2)]
    public float y;
    [InputControl(name="handPosition/z", offset = 3)]
    public float z;

    [InputControl(name="handRotation", layout="Vector3")]
    [InputControl(name="handRotation/x")]
    public float rx;
    [InputControl(name="handRotation/y")]
    public float ry;
    [InputControl(name="handRotation/z")]
    public float rz;
}

[InputControlLayout(stateType = typeof(GlovesyState))]
[InitializeOnLoad]
public class Glovesy : InputDevice {
    [InputControl]
    public Vector3Control handPosition {
        get;
        private set;
    }
    [InputControl]
    public Vector3Control handRotation {
        get;
        private set;
    }

    protected override void FinishSetup() {
        base.FinishSetup();

        handPosition = GetChildControl<Vector3Control>("handPosition"); 
        handRotation = GetChildControl<Vector3Control>("handRotation");
    }


    static Glovesy() {
        InputSystem.RegisterLayout<Glovesy>();
        //if(!InputSystem.devices.Any(x => x is Glovesy))
        foreach(InputDevice x in InputSystem.devices) {
            
            if (!(x is Glovesy))
                InputSystem.AddDevice<Glovesy>();
        }
    }

    [MenuItem("Tools/Add Glovesy")]
    public static void initialize() {
        InputSystem.AddDevice<Glovesy>();
    }
}

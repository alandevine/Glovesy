using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class MainMenu : MonoBehaviour {
    public void StartDemo() {
        SceneManager.LoadScene("SampleScene");
    }

    public void Calibrate() {
        SceneManager.LoadScene("Calibrate");
    }

    public void Quit() {
        Application.Quit();
    }
}


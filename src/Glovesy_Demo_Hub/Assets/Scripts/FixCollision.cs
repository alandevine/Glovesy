using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FixCollision : MonoBehaviour
{
    void OnCollisionEnter(Collision other) {
        if (other.gameObject.tag == "Hand") {
            Physics.IgnoreCollision(other.collider, GetComponent<Collider>());
        }
    }
        
}

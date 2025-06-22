using UnityEngine;
using UnityEngine.UI;
using TMPro;
   
public class LivesUI : MonoBehaviour
{
    public TextMeshProUGUI livesText; // Corrected variable name

    void Update()
    {
        livesText.text = PlayerStats.Lives + " Lives"; // Corrected accessing text component
    }
}

using UnityEngine;
using UnityEngine.UI;
using TMPro;
using System; // For Action
using System.Collections.Generic; // Correct namespace
using System.Collections; // Optional: only if you use coroutines later

public class KeyBoardKey : MonoBehaviour
{
    [Header("Elements")]
    [SerializeField] private TextMeshProUGUI letterText;

    [Header("Events")]
    public static Action<char> OnKeyPressed;
    public static event Action OnBackspacePressed;

    void Start()
    {
        GetComponent<Button>().onClick.AddListener(SendKeyPressedEvent);
    }

    void SendKeyPressedEvent()
    {
        if (!string.IsNullOrEmpty(letterText.text))
        {
            OnKeyPressed?.Invoke(letterText.text[0]);
        }
    }
}

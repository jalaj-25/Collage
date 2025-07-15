using UnityEngine;
using UnityEngine.UI;
using TMPro;
using System; // For Action
using System.Collections.Generic; // Correct namespace
using System.Collections; // Optional: only if you use coroutines later

enum Validity {None, Valid, Potential, Invalid}
public class KeyBoardKey : MonoBehaviour
{
    [Header("Elements")]
    [SerializeField] private Image renderer;
    [SerializeField] private TextMeshProUGUI letterText;

    [Header("Settings")]
    private Validity validity;

    [Header("Events")]
    public static Action<char> OnKeyPressed;
    public static event Action OnBackspacePressed;

    void Start()
    {
        GetComponent<Button>().onClick.AddListener(SendKeyPressedEvent);
        Initialize();
    }

    void SendKeyPressedEvent()
    {
        if (!string.IsNullOrEmpty(letterText.text))
        {
            OnKeyPressed?.Invoke(letterText.text[0]);
        }
    }

    public char GetLetter()
    {
        return letterText.text[0];
    }

    public void Initialize()
    {
        renderer.color = Color.white; // Reset color to default
        validity = Validity.None;
    }

    public void SetValid()
    {
        renderer.color = Color.green; // Example color for valid key
        validity = Validity.Valid;
    }

    public void SetPotential()
    {
        renderer.color = Color.yellow; // Example color for potential key
        validity = Validity.Potential;
    }

    public void SetInvalid()
    {
        if(validity == Validity.Valid || validity == Validity.Potential)
            return; // Avoid redundant calls
        renderer.color = Color.gray; // Example color for invalid key
        validity = Validity.Invalid;
    }
}

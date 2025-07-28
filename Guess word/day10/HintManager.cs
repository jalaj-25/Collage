using UnityEngine;
using System.Collections.Generic;
using TMPro;
using Unity.UI;
public class HintManager : MonoBehaviour
{
    [Header("Hint Settings")]
    [SerializeField] private GameObject keyboard;
    private KeyBoardKey[] keys;

    [Header("UI References")]
    [SerializeField] private TextMeshProUGUI KeyboardPriceText;
    [SerializeField] private TextMeshProUGUI letterPriceText;

    [Header("Settings")]
    [SerializeField] private int keyboardHintPrice;
    [SerializeField] private int letterHintPrice;
    private bool shouldReset;
    private void Awake()
    {
        keys = keyboard.GetComponentsInChildren<KeyBoardKey>();
    }
    
    private void Start()
    {
        KeyboardPriceText.text = keyboardHintPrice.ToString();
        letterPriceText.text = letterHintPrice.ToString();
        GameManager.OnGameStateChanged += GameStateChangedCallBack;
    }

    private void OnDestroy()
    {
        GameManager.OnGameStateChanged -= GameStateChangedCallBack;
    }

    private void GameStateChangedCallBack(GameState gameState)
    {
        switch (gameState)
        {
            case GameState.Menu:
                
                break;

            case GameState.Game:
                if(shouldReset)
                {
                    letterHintGivenIndex.Clear();
                    shouldReset = false;
                }
                else
                {
                    shouldReset = true;
                }
                break;

            case GameState.LevelComplete:
                shouldReset = true;
                break;

            case GameState.GameOver:
                shouldReset = true;
                break;
        }
    }

    public void KeyBoardHint()
    {
        if(DataManager.instance.GetCoin() < keyboardHintPrice)
        {
            Debug.Log("Not enough coins for keyboard hint.");
            return;
        }
        string secretWord = WordManager.Instance.GetSecretWord();
        List<KeyBoardKey> untouchedKeys = new List<KeyBoardKey>();

        // Collect all untouched keys
        foreach (var key in keys)
        {
            if (key.IsUntouchedKeys)
                untouchedKeys.Add(key);
        }

        // Remove keys that are part of the secret word
        List<KeyBoardKey> nonWordKeys = new List<KeyBoardKey>(untouchedKeys);
        foreach (var key in untouchedKeys)
        {
            if (secretWord.Contains(key.GetLetter().ToString()))
                nonWordKeys.Remove(key);
        }

        if (nonWordKeys.Count <= 0)
        {
            Debug.Log("No more non-secret letters to reveal.");
            return;
        }

        // Pick a random incorrect key and mark it as invalid
        int randomKeyIndex = Random.Range(0, nonWordKeys.Count);
        var hintKey = nonWordKeys[randomKeyIndex];
        hintKey.SetInvalid();
        Debug.Log($"Keyboard Hint: '{hintKey.GetLetter()}' is not in the word.");

        DataManager.instance.RemoveCoin(keyboardHintPrice);
    }

    List<int> letterHintGivenIndex = new List<int>();
    public void LetterHint()
    {

        if(DataManager.instance.GetCoin() < letterHintPrice)
        {
            Debug.Log("Not enough coins for letter hint.");
            return;
        }

        if (letterHintGivenIndex.Count >= 5)
        {
            Debug.Log("All letters have been hinted.");
            return;
        }

        List<int> letterHintNotGivenIndeices = new List<int>();
        for(int i = 0; i < 5; i++)
        {
            if (!letterHintGivenIndex.Contains(i))
            {
                letterHintNotGivenIndeices.Add(i);
            }
        }

        WordContainer currentWordContainer = InputManager.Instance.GetCurrentWordContainer();
        string secretWord = WordManager.Instance.GetSecretWord();
        int randomIndex = letterHintNotGivenIndeices[Random.Range(0, letterHintNotGivenIndeices.Count)];
        letterHintGivenIndex.Add(randomIndex);
        currentWordContainer.AddAsHint(randomIndex, secretWord[randomIndex]);

        DataManager.instance.RemoveCoin(letterHintPrice);
    }
}

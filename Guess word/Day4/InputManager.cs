using UnityEngine;
using UnityEngine.InputSystem;
using UnityEngine.UI;
using System.Collections;
using System.Collections.Generic;

public class InputManager : MonoBehaviour
{
    [Header("Element")]
    [SerializeField] private WordContainer[] wordContainers;
    [SerializeField] private Button tryButton;
    [SerializeField] private KeyboardColorizer keyboardColorizer;

    [Header("Settings")]
    private int currentWordContainerIndex = 0;
    private bool canAddLetter = true;

    void Start()
    {
        Initialize();
        KeyBoardKey.OnKeyPressed += KeyPressedCallBack;
    }

    void OnDestroy()
    {
        KeyBoardKey.OnKeyPressed -= KeyPressedCallBack;
    }

    public void Initialize()
    {
        for (int i = 0; i < wordContainers.Length; i++)
        {
            wordContainers[i].Initialize();
        }
    }

    private void KeyPressedCallBack(char letter)
    {
        if (!canAddLetter || currentWordContainerIndex >= wordContainers.Length)
            return;

        wordContainers[currentWordContainerIndex].Add(letter);

        if (wordContainers[currentWordContainerIndex].IsComplete())
        {
            canAddLetter = false;
            EnableTryButton();
            CheckWord(); // Automatically check the word when complete
        }
    }

    public void CheckWord()
    {
        string wordToCheck = wordContainers[currentWordContainerIndex].GetWord();
        string secretWord = WordManager.Instance.GetSecretWord();
        
        wordContainers[currentWordContainerIndex].Colorize(secretWord);
        keyboardColorizer.Colorizer(secretWord, wordToCheck);
        if (wordToCheck == secretWord)
        {
            SetLevelComplete();
        }
        else
        {
            Debug.Log("❌ Incorrect Word!");
            DisableTryButton();
            currentWordContainerIndex++;
            canAddLetter = true;
        }
    }

    private void SetLevelComplete()
    {
        GameManager.Instance.SetGameState(GameState.LevelComplete);
    }

    public void BackSpacePressedCallBack()
    {
        bool removeLetter = wordContainers[currentWordContainerIndex].RemoveLetter();
        if (!removeLetter)
        {
            DisableTryButton();
        }
        canAddLetter = true;
    }

    public void EnableTryButton()
    {
        tryButton.interactable = true;
    }

    public void DisableTryButton()
    {
        tryButton.interactable = false;
    }
}

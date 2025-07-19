using UnityEngine;
using UnityEngine.InputSystem;
using UnityEngine.UI;
using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;

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
        GameManager.OnGameStateChanged += GameStateChangedCallBack;
    }

    void OnDestroy()
    {
        KeyBoardKey.OnKeyPressed -= KeyPressedCallBack;
        GameManager.OnGameStateChanged -= GameStateChangedCallBack;
    }

    private void GameStateChangedCallBack(GameState gameState)
    {
       switch(gameState)
        {
            case GameState.Game:
                Initialize();
                break;

            case GameState.LevelComplete:
                break;
        }
    }

    public void Initialize()
    {
        currentWordContainerIndex = 0;
        canAddLetter = true;

        DisableTryButton();
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
            Debug.Log("Wrong word");
            currentWordContainerIndex++;
            DisableTryButton();

            if(currentWordContainerIndex >= wordContainers.Length)
            {
                GameManager.Instance.SetGameState(GameState.GameOver);
                return;
            } else
            {
                canAddLetter = true;
            }
        }
    }

    private void SetLevelComplete()
    {
        UpdateData();
        GameManager.Instance.SetGameState(GameState.LevelComplete);
    }

    private void UpdateData()
    {
        int scoreToAdd = 6 - currentWordContainerIndex;

        DataManager.instance.IncreaseScore(scoreToAdd);
        DataManager.instance.AddCoin(scoreToAdd * 3);
    }

    public void BackSpacePressedCallBack()
    {
        //if(!GameManager.Instance.IsGameState())
            //return;
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

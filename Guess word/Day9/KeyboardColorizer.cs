using UnityEngine;

public class KeyboardColorizer : MonoBehaviour
{
    [Header("Elements")]
    private KeyBoardKey[] keys;
    private bool shouldReset;
    private void Start()
    {
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
            case GameState.Game:
                if (shouldReset)
                {
                    Initialize();
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

    private void Initialize()
    {
        for (int i = 0; i < keys.Length; i++)
        {
            keys[i].Initialize();
        }
        shouldReset = false;

    }
    private void Awake()
    {
        keys = GetComponentsInChildren<KeyBoardKey>();
    }
    public void Colorizer(string secretWord, string wordToCheck)
    {
        for(int i = 0; i < keys.Length; i++)
        {
            char keyLetter = keys[i].GetLetter();

            for(int j = 0; j < wordToCheck.Length; j++)
            {
                if (keyLetter != wordToCheck[j])
                {
                    continue;
                }
                if (keyLetter == secretWord[j])
                {
                    keys[i].SetValid();
                }
                else if(secretWord.Contains(keyLetter))
                {
                    // Correct letter but in the wrong position
                    keys[i].SetPotential();
                }
                else
                {
                    // Incorrect letter
                    keys[i].SetInvalid();
                }
            }
        }
    }
}

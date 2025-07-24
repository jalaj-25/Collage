using UnityEngine;

public class WordManager : MonoBehaviour
{
    public static WordManager Instance;
    [Header("Element")]
    [SerializeField] private string secretWord;
    [SerializeField] private TextAsset wordsText;
    private string words;

    [Header("Settings")]
    private bool shouldReset;
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    private void Awake()
    {
        if(Instance == null)
        {
            Instance = this;
        }
        else
        {
            Destroy(gameObject);
        }

        words = wordsText.text;
    }

    private void Start()
    {
        SetNewSecretWord();
        GameManager.OnGameStateChanged += GameStateChangedCallBack;
    }
    private void OnDestroy()
    {
        SetNewSecretWord();
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
                    SetNewSecretWord();
                    shouldReset = false;
                }
                else
                {
                    shouldReset = true;
                }
                break;

            case GameState.Play:

                break;

            case GameState.LevelComplete:
                shouldReset = true;
                break;

            case GameState.GameOver:
                shouldReset = true;
                break;

            case GameState.Idle:

                break;
        }
    }

    public string GetSecretWord()
    {
        return secretWord.ToUpper();
    }   

    public string SetNewSecretWord() { 
        Debug.Log("String lenght: " + words.Length);
        int wordCount = (words.Length + 2) / 7;
        int wordIndex = Random.Range(0, wordCount);
        int wordStartIndex = wordIndex * 7;
        secretWord = words.Substring(wordStartIndex, 5).ToUpper();

        shouldReset = false;
        Debug.Log("New secret word: " + secretWord);
        return secretWord;
    }
}

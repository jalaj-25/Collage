using UnityEngine;
using TMPro;
public class UIManager : MonoBehaviour
{
    public static UIManager Instance { get; private set; }

    [Header("UI Elements")]
    [SerializeField] private CanvasGroup gameCG;
    [SerializeField] private CanvasGroup menuCG;
    [SerializeField] private CanvasGroup levelCompleteGG;
    [SerializeField] private CanvasGroup gameOverCG;
    
    [Header("Level Complete UI")]
    [SerializeField] private TextMeshProUGUI menuCoins;
    [SerializeField] private TextMeshProUGUI menuBestScore;

    [Header("Level Complete UI")]
    [SerializeField] private TextMeshProUGUI levelCompleteCoins;
    [SerializeField] private TextMeshProUGUI levelCompleteSecretWord;
    [SerializeField] private TextMeshProUGUI levelCompleteScore;
    [SerializeField] private TextMeshProUGUI levelCompleteBestScore;

    [Header("GameOver UI")]
    [SerializeField] private TextMeshProUGUI gameOverCoin;
    [SerializeField] private TextMeshProUGUI gameOverSecretWord;
    [SerializeField] private TextMeshProUGUI gameOverBestScore;

    [Header("Game Elements")]
    [SerializeField] private TextMeshProUGUI gameScore;
    [SerializeField] private TextMeshProUGUI gameCoins;

    private void Start()
    {
        ShowGame();
        HideLevelComplete ();
        GameManager.OnGameStateChanged += GameStateChangedCallBack;
    }

    void OnDestroy()
    {
        GameManager.OnGameStateChanged -= GameStateChangedCallBack;
    }


    private void GameStateChangedCallBack(GameState gameState)
    {
        switch (gameState)
        {
            case GameState.Menu:
                HideGame();
                HideLevelComplete();
                break;
            case GameState.Game:
                ShowGame();
                HideLevelComplete();
                break;
            case GameState.Play:
                ShowGame();
                HideLevelComplete();
                break;
            case GameState.LevelComplete:
                ShowLevelComplete();
                HideGame();
                break;
            case GameState.GameOver:
                ShowGameOver();
                HideGame();
                break;
            case GameState.Idle:
                HideGame();
                HideLevelComplete();
                break;
        }
    }

    private void Awake()
    {
        // Ensure that there is only one instance of UIManager
        if (Instance == null)
        {
            Instance = this;
        }
        else
        {
            Destroy(gameObject);
        }
    }

    private void ShowGame()
    {
        gameCoins.text = DataManager.instance.GetCoin().ToString();
        gameScore.text = DataManager.instance.GetScore().ToString();
        ShowCG(gameCG);
    }

    private void HideGame()
    {
        HideCG(gameCG);
    }

    private void ShowGameOver()
    {
        gameOverCoin.text = DataManager.instance.GetCoin().ToString();
        gameOverSecretWord.text = DataManager.instance.SecretWord;
        gameOverBestScore.text = DataManager.instance.GetBestScore().ToString();
        ShowCG(levelCompleteGG);
    }

    private void ShowLevelComplete()
    {
        levelCompleteCoins.text = DataManager.instance.GetCoin().ToString();
        levelCompleteSecretWord.text = DataManager.instance.SecretWord;
        levelCompleteScore.text = DataManager.instance.GetScore().ToString();
        levelCompleteBestScore.text = DataManager.instance.GetBestScore().ToString();
        ShowCG(levelCompleteGG);
    }

    private void HideLevelComplete()
    {
        HideCG(levelCompleteGG);
    }

    private void ShowCG(CanvasGroup cg)
    {
        cg.alpha = 1;
        cg.interactable = true;
        cg.blocksRaycasts = true;
    }
    private void HideCG(CanvasGroup cg)
    {
        cg.alpha = 0;
        cg.interactable = false;
        cg.blocksRaycasts = false;
    }
}
using UnityEngine;
using TMPro;

public class UIManager : MonoBehaviour
{
    public static UIManager Instance { get; private set; }

    [Header("Menu UI")]
    [SerializeField] private CanvasGroup menuCG;
    [SerializeField] private TextMeshProUGUI menuCoins;
    [SerializeField] private TextMeshProUGUI menuBestScore;

    [Header("Game UI")]
    [SerializeField] private CanvasGroup gameCG;
    [SerializeField] private TextMeshProUGUI gameScore;
    [SerializeField] private TextMeshProUGUI gameCoins;

    [Header("Level Complete UI")]
    [SerializeField] private CanvasGroup levelCompleteGG;
    [SerializeField] private TextMeshProUGUI levelCompleteCoins;
    [SerializeField] private TextMeshProUGUI levelCompleteSecretWord;
    [SerializeField] private TextMeshProUGUI levelCompleteScore;
    [SerializeField] private TextMeshProUGUI levelCompleteBestScore;

    [Header("Game Over UI")]
    [SerializeField] private CanvasGroup gameOverCG;
    [SerializeField] private TextMeshProUGUI gameOverCoin;
    [SerializeField] private TextMeshProUGUI gameOverSecretWord;
    [SerializeField] private TextMeshProUGUI gameOverBestScore;

    private void Awake()
    {
        if (Instance == null)
        {
            Instance = this;
        }
        else
        {
            Destroy(gameObject);
        }
    }

    private void Start()
    {
        ShowMenu();
        HideGame();
        HideLevelComplete();
        HideGameOver();

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
                ShowMenu();
                HideGame();
                HideLevelComplete();
                HideGameOver();
                break;

            case GameState.Game:
                ShowGame();
                HideMenu();
                HideLevelComplete();
                HideGameOver();
                break;

            case GameState.Play:
                ShowGame();
                HideMenu();
                HideLevelComplete();
                break;

            case GameState.LevelComplete:
                ShowLevelComplete();
                HideMenu();
                HideGame();
                break;

            case GameState.GameOver:
                ShowGameOver();
                HideGame();
                break;

            case GameState.Idle:
                HideGame();
                HideLevelComplete();
                HideGameOver();
                break;
        }
    }

    private void ShowMenu()
    {
        if (DataManager.instance != null)
        {
            menuCoins.text = DataManager.instance.GetCoin().ToString();
            menuBestScore.text = DataManager.instance.GetBestScore().ToString();
        }
        ShowCG(menuCG);
    }

    private void HideMenu()
    {
        HideCG(menuCG);
    }

    private void ShowGame()
    {
        if (DataManager.instance != null)
        {
            gameCoins.text = DataManager.instance.GetCoin().ToString();
            gameScore.text = DataManager.instance.GetScore().ToString();
        }
        ShowCG(gameCG);
    }

    private void HideGame()
    {
        HideCG(gameCG);
    }

    private void ShowLevelComplete()
    {
        if (DataManager.instance != null)
        {
            levelCompleteCoins.text = DataManager.instance.GetCoin().ToString();
            levelCompleteSecretWord.text = DataManager.instance.SecretWord;
            levelCompleteScore.text = DataManager.instance.GetScore().ToString();
            levelCompleteBestScore.text = DataManager.instance.GetBestScore().ToString();
        }
        ShowCG(levelCompleteGG);
    }

    private void HideLevelComplete()
    {
        HideCG(levelCompleteGG);
    }

    private void ShowGameOver()
    {
        if (DataManager.instance != null)
        {
            gameOverCoin.text = DataManager.instance.GetCoin().ToString();
            gameOverSecretWord.text = DataManager.instance.SecretWord;
            gameOverBestScore.text = DataManager.instance.GetBestScore().ToString();
        }
        ShowCG(gameOverCG);
    }

    private void HideGameOver()
    {
        HideCG(gameOverCG);
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

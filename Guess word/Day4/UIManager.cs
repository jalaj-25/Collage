using UnityEngine;

public class UIManager : MonoBehaviour
{
    public static UIManager Instance { get; private set; }

    [Header("UI Elements")]
    [SerializeField] private CanvasGroup gameCG;
    [SerializeField] private CanvasGroup levelCompleteGG;

    private void Start()
    {
        GameManager.OnGameStateChanged += GameStateChangedCallBack;
    }

    void OnDestroy()
    {
        //KeyBoardKey.OnKeyPressed -= KeyPressedCallBack;
    }


    private void GameStateChangedCallBack(GameState gameState)
    {
        switch (gameState)
        {
            case GameState.Menu:
                HideGame();
                HideLevelComplete();
                break;
            case GameState.Play:
                ShowGame();
                HideLevelComplete();
                break;
            case GameState.LevelComplete:
                HideGame();
                ShowLevelComplete();
                break;
            case GameState.GameOver:
                HideGame();
                HideLevelComplete();
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
        ShowCG(gameCG);
    }

    private void HideGame()
    {
        HideCG(gameCG);
    }

    private void ShowLevelComplete()
    {
        ShowCG(levelCompleteGG);
    }

    private void HideLevelComplete()
    {
        HideCG(levelCompleteGG);
    }

    private void ShowCG(CanvasGroup cg)
    {
        cg.alpha = 1f;
        cg.interactable = true;
        cg.blocksRaycasts = true;
    }
    private void HideCG(CanvasGroup cg)
    {
        cg.alpha = 0f;
        cg.interactable = false;
        cg.blocksRaycasts = false;
    }
}
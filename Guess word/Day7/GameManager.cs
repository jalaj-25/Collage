using UnityEngine;
using System;

public enum GameState
{
    Menu,
    Play,
    LevelComplete,
    GameOver,
    Idle,
    Game
}
public class GameManager : MonoBehaviour
{
    public static GameManager Instance;

    [Header("Setting")]
    private GameState gameState;

    [Header("Event")]
    public static Action<GameState> OnGameStateChanged;
    // Start is called once before the first execution of Update after the MonoBehaviour is created
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

    // Update is called once per frame
    public void SetGameState(GameState gameState)
    {
        this.gameState = gameState;
        OnGameStateChanged?.Invoke(gameState);
    }

    public void NextButtonCallBack()
    {
        SetGameState(GameState.Game);
    }

    public bool IsGameState(GameState state)
    {
        return gameState == GameState.Game;
    }
}

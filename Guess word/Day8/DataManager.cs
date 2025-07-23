using UnityEngine;

public class DataManager : MonoBehaviour
{
    public static DataManager instance;

    [Header("Data")]
    private int coin;
    private int score;
    private int bestScore;

    public string SecretWord => WordManager.Instance.GetSecretWord();

    private void Awake()
    {
        if (instance == null)
        {
            instance = this;
        }
        else
        {
            Destroy(gameObject);
        }
        LoadData();
    }

    public void AddCoin(int amount)
    {
        coin += amount;
        SaveData();
    }

    public void RemoveCoin(int amount)
    {
        coin = Mathf.Max(coin - amount, 0);
        SaveData();
    }

    public void IncreaseScore(int amount)
    {
        score += amount;
        if (score > bestScore)
        {
            bestScore = score;
        }
        SaveData();
    }

    public int GetCoin() => coin;
    public int GetScore() => score;
    public int GetBestScore() => bestScore;

    public void ResetScore()
    {
        score = 0;
        SaveData();
    }

    public void LoadData()
    {
        if (!PlayerPrefs.HasKey("Coin")) PlayerPrefs.SetInt("Coin", 150);
        coin = PlayerPrefs.GetInt("Coin");
        score = PlayerPrefs.GetInt("Score");
        bestScore = PlayerPrefs.GetInt("BestScore");
    }

    public void SaveData()
    {
        PlayerPrefs.SetInt("Coin", coin);
        PlayerPrefs.SetInt("Score", score);
        PlayerPrefs.SetInt("BestScore", bestScore);
        PlayerPrefs.Save();
    }
}

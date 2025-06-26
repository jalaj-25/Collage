using UnityEngine;
using UnityEngine.SceneManagement;
using TMPro;

public class GameOver : MonoBehaviour
{
    public SceneFader sceneFader;
    
    public TextMeshProUGUI roundsText;

    public string menuSceneName = "1stLevel";

    void OnEnable()
    {
        roundsText.text = PlayerStats.Rounds.ToString();
    }

    public void Retry()
    {
        sceneFader.FadeTo(SceneManager.GetActiveScene().name);
    }

    public void Menu()
    {
        sceneFader.FadeTo(SceneManager.GetActiveScene().name);
    }
}

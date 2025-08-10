using UnityEngine;
using TMPro;

public class ScoreUIManager : MonoBehaviour
{
    public TextMeshProUGUI currentScoreText;
    public TextMeshProUGUI maxScoreText;

    private int maxScore;

    private void Start()
    {
        // Load saved max score from PlayerPrefs (default 0 if none)
        maxScore = PlayerPrefs.GetInt("MaxScore", 0);
        UpdateMaxScoreUI();
    }

    public void UpdateCurrentScore(int score)
    {
        currentScoreText.text = "Score: " + score.ToString();

        if (score > maxScore)
        {
            maxScore = score;
            PlayerPrefs.SetInt("MaxScore", maxScore); // Save to disk
            PlayerPrefs.Save();
            UpdateMaxScoreUI();
        }
    }

    private void UpdateMaxScoreUI()
    {
        maxScoreText.text = "Max Score: " + maxScore.ToString();
    }

    public void ResetScores()
    {
        UpdateCurrentScore(0);
    }
}

using UnityEngine;
using UnityEngine.UI;
using TMPro;
using System.Collections;

public class RoundsSurvived : MonoBehaviour
{
    public TextMeshProUGUI roundsText;

    void OnEnable()
    {
        StartCoroutine(AnimateText());
        roundsText.text = PlayerStats.Rounds.ToString();
    }

    IEnumerator AnimateText()
    {
        roundsText.text = "0";
        int rounds = 0;

        yield return new WaitForSeconds(0.7f);

        while (rounds < PlayerStats.Rounds)
        {
            rounds++;
            roundsText.text = rounds.ToString();

            yield return new WaitForSeconds(0.5f);
        }
    }
}

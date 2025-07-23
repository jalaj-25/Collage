using UnityEngine;
using TMPro;
using UnityEngine.UI;
public class LetterContainer : MonoBehaviour
{
    [Header("Elements")]
    [SerializeField] private SpriteRenderer letterContainer;
    [SerializeField] private TextMeshPro letter;

    public void Initialize()
    {
        letter.text = " ";
        letterContainer.color = Color.white;
    }

    public void SetLetter(char letter, bool isHint = false)
    {
        if(isHint)
            this.letter.color = Color.gray;
        else
            this.letter.color = Color.black;

        this.letter.text = letter.ToString();
    }

    public void SetValid()
    {
        letterContainer.color = Color.green;
    }

    public void SetInvalid()
    {
        letterContainer.color = Color.red;
    }

    public void SetPotential()
    {
        letterContainer.color = Color.yellow;
    }

    public char GetLetter()
    {
        return letter.text[0];
    }
}

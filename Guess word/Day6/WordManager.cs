using UnityEngine;

public class WordManager : MonoBehaviour
{
    public static WordManager Instance;
    [Header("Element")]
    [SerializeField] private string secretWord;
    [SerializeField] private TextAsset wordsText;
    private string[] words;
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
    }

    public string GetSecretWord()
    {
        return secretWord.ToUpper;
    }   

    public string SetNewSecretWord() { 
        Debug.Log("String lenght: " + words.Length);
        int wordCount = (words.Length + 2) / 7;
        int wordIndex = Random.Range(0, wordCount);
        int wordStartIndex = wordIndex * 7;
        secretWord = words.Substring(wordStartIndex, 5).ToUpper();
    }
}

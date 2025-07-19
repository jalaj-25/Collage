using UnityEngine;

public class WordManager : MonoBehaviour
{
    public static WordManager Instance;
    [Header("Element")]
    [SerializeField] private string secretWord;
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
    }

    public string GetSecretWord()
    {
        return secretWord;
    }   
}

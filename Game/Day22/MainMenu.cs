using UnityEngine;
using UnityEngine.SceneManagement;

public class MainMenu : MonoBehaviour
{
    public SceneFader sceneFader;
    public string levelToLoad = "1stLevel";
    public void Play()
    {
        sceneFader.FadeTo(levelToLoad);
    }

    public void Quit() {
        Debug.Log("Quit");
        Application.Quit();
    }
}
  
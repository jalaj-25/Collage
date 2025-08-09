using UnityEngine;
using UnityEngine.SceneManagement;

public class pauseMenu : MonoBehaviour
{
    public GameObject pauseMenuUI;
    public GameObject MainMenuUI;
    public GameObject snakeAnimUI;
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape) || Input.GetKeyDown(KeyCode.P))
        {
            Toggle();
        }
    }

    public void Toggle()
    {
        bool isPaused = !pauseMenuUI.activeSelf;
        pauseMenuUI.SetActive(isPaused);

        if (snakeAnimUI != null)
        {
            snakeAnimUI.SetActive(isPaused); 
        }

        Time.timeScale = isPaused ? 0f : 1f;
    }

    public void Retry()
    {
        Toggle();
        SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex);
    }

    public void Resume()
    {
        Toggle();
    }

    public void Menu()
    {
        Time.timeScale = 0f; 
        SceneManager.LoadScene("menu");
    }
}

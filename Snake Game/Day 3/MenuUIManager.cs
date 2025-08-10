using UnityEngine;

public class MenuUIManager : MonoBehaviour
{
    public GameObject mainMenuPanel;
    public GameObject skinsPanel;

    public void OpenSkins()
    {
        mainMenuPanel.SetActive(false);
        skinsPanel.SetActive(true);
    }

    public void BackToMainMenu()
    {
        skinsPanel.SetActive(false);
        mainMenuPanel.SetActive(true);
    }
}

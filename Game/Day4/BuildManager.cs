using UnityEngine;

public class BuildManager : MonoBehaviour
{
    public static BuildManager instance;

    void Awake()
    {
        if (instance != null)
        {
            UnityEngine.Debug.Log("More than one BuildManager in scene!");
            return;
        }
        instance = this;
    }

    [Header("Turret Prefabs")]
    public GameObject standardTurretPrefab; // Moved out of private scope and made public for assignment in Inspector
    public GameObject anotherTurretPrefab;

    private GameObject turretToBuild;

    public GameObject GetTurretToBuild()
    {
        return turretToBuild;
    }

    public void SetTurretToBuild(GameObject turret)
    {
       turretToBuild = turret;
    }
}

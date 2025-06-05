using UnityEngine;

public class Shop : MonoBehaviour
{
    BuildManager buildManager;

    void Start()
    {
        buildManager = BuildManager.instance;
    }

    public void PurchaseStandardTurret()
    {
        Debug.Log("Standard turret purchased.");
        buildManager.SetTurretToBuild(buildManager.standardTurretPrefab);
    }

    public void PurchaseMissile()
    {
        Debug.Log("Missile turret purchased.");
        buildManager.SetTurretToBuild(buildManager.anotherTurretPrefab);
    }
}

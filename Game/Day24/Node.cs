using UnityEngine;
using UnityEngine.EventSystems;

public class Node : MonoBehaviour
{
    public Color hoverColor;
    public Vector3 positionOffset;

    [HideInInspector]
    public GameObject turret;
    [HideInInspector]
    public TurretBlueprint turretBlueprint;

    [HideInInspector]
    public bool isSilverUpgraded = false;  // ✅ consistent naming
    [HideInInspector]
    public bool isGoldUpgraded = false;

    private Renderer rend;
    private Color startColor;
    public Color notEnoghMoneyColor;
    public AudioClip myClip;
    private BuildManager buildManager;

    public AudioClip silverUpgradeClip;
    public AudioClip goldUpgradeClip;


    void Start()
    {
        rend = GetComponent<Renderer>();
        startColor = rend.material.color;
        buildManager = BuildManager.instance;
    }

    public Vector3 GetBuildPosition()
    {
        return transform.position + positionOffset;
    }

    void OnMouseDown()
    {
        if (EventSystem.current.IsPointerOverGameObject())
            return;

        if (turret != null)
        {
            buildManager.SelectNode(this);
            return;
        }

        if (!buildManager.CanBuild)
            return;

        BuildTurret(buildManager.GetTurretToBuild());
    }

    void BuildTurret(TurretBlueprint blueprint)
    {
        if (PlayerStats.Money < blueprint.cost)
        {
            Debug.Log("Not enough money.");
            return;
        }

        PlayerStats.Money -= blueprint.cost;

        GameObject _turret = Instantiate(blueprint.prefab, GetBuildPosition(), Quaternion.identity);
        turret = _turret;
        turretBlueprint = blueprint;

        GameObject effect = Instantiate(buildManager.buildEffect, GetBuildPosition() + new Vector3(0f, 1.5f, 0f), Quaternion.identity);
        Destroy(effect, 5f);

        Debug.Log("Turret built.");
        if (myClip != null)
        {
            AudioSource.PlayClipAtPoint(myClip, Camera.main.transform.position, 1.0f);

        }
    }

    public void UpgradeToSilver()
    {
        if (isSilverUpgraded)
        {
            Debug.Log("Already upgraded to Silver.");
            return;
        }

        if (PlayerStats.Money < turretBlueprint.upgradeCostToSilver)
        {
            Debug.Log("Not enough money for Silver upgrade.");
            return;
        }

        PlayerStats.Money -= turretBlueprint.upgradeCostToSilver;

        Destroy(turret);
        GameObject _turret = Instantiate(turretBlueprint.upgradedPrefabToSilver, GetBuildPosition(), Quaternion.identity);
        turret = _turret;

        GameObject effect = Instantiate(buildManager.buildEffect, GetBuildPosition(), Quaternion.identity);
        Destroy(effect, 5f);
        
        if (silverUpgradeClip != null)
        {
            AudioSource.PlayClipAtPoint(silverUpgradeClip, Camera.main.transform.position, 1.0f);
        }

        isSilverUpgraded = true;
        Debug.Log("Turret upgraded to Silver.");
    }

    public void UpgradeToGold()
    {
        if (!isSilverUpgraded)
        {
            Debug.Log("First upgrade to Silver.");
            return;
        }

        if (isGoldUpgraded)
        {
            Debug.Log("Already upgraded to Gold.");
            return;
        }

        if (PlayerStats.Money < turretBlueprint.upgradeCostToGold)
        {
            Debug.Log("Not enough money for Gold upgrade.");
            return;
        }

        PlayerStats.Money -= turretBlueprint.upgradeCostToGold;

        Destroy(turret);
        GameObject _turret = Instantiate(turretBlueprint.upgradedPrefabToGold, GetBuildPosition(), Quaternion.identity);
        turret = _turret;

        GameObject effect = Instantiate(buildManager.buildEffect, GetBuildPosition(), Quaternion.identity);
        Destroy(effect, 5f);

        if (goldUpgradeClip != null)
        {
            AudioSource.PlayClipAtPoint(goldUpgradeClip, Camera.main.transform.position, 1.0f);
        }

        isGoldUpgraded = true;
        Debug.Log("Turret upgraded to Gold.");
    }

    public void SellTurret()
    {
        GameObject effect = Instantiate(buildManager.sellEffect, GetBuildPosition(), Quaternion.identity);
        Destroy(effect, 5f);

        PlayerStats.Money += turretBlueprint.GetSellAmount();
        Destroy(turret);
        turretBlueprint = null;

        // ✅ Reset upgrade states after selling
        isSilverUpgraded = false;
        isGoldUpgraded = false;
    }

    void OnMouseEnter()
    {
        if (EventSystem.current.IsPointerOverGameObject())
            return;

        if (!buildManager.CanBuild)
            return;

        rend.material.color = buildManager.HasMoney ? hoverColor : notEnoghMoneyColor;
    }

    void OnMouseExit()
    {
        rend.material.color = startColor;
    }
}

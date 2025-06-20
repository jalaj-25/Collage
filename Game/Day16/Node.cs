using UnityEngine;
using UnityEngine.EventSystems;

public class Node : MonoBehaviour
{
    public Color hoverColor;
    public Vector3 positionOffset;

    [Header("Optional")]
    public GameObject turret;
    
    private Renderer rend;
    private Color startColor;
    public Color notEnoghMoneyColor;

    private BuildManager buildManager;

    void Start()
    {
        rend = GetComponent<Renderer>();
        startColor = rend.material.color;

        // ✅ Initialize buildManager
        buildManager = BuildManager.instance;
    }

    public Vector3 GetBuildPosition()
    {
        return transform.position + positionOffset;
    }
    void OnMouseDown()
    {
        if (EventSystem.current.IsPointerOverGameObject())
        {
            return;
        }

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
            Debug.Log("Not enogh paisaaa.");
            return;
        }

        PlayerStats.Money -= blueprint.cost;
        GameObject _turret = Instantiate(blueprint.prefab, GetBuildPosition(), Quaternion.identity);
        turret = _turret;
        GameObject effect = Instantiate(buildManager.buildEffect, GetBuildPosition(), Quaternion.identity);
        Destroy(effect, 5f);
        Debug.Log("Turret build");
    }

    void OnMouseEnter()
    {
        if (EventSystem.current.IsPointerOverGameObject()) // ✅ Correct
        {
            return;
        }
        if (!buildManager.CanBuild)
            return;
        if(buildManager.HasMoney)
        {
            rend.material.color = hoverColor;
        }
        else
        {
            rend.material.color = notEnoghMoneyColor;   
        }
    }

    void OnMouseExit()
    {
        rend.material.color = startColor;
    }
}

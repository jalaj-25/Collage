using UnityEngine;

public class Node : MonoBehaviour
{
    public Color hoverColor;
    public Vector3 positionOffset;

    [Header("Optional")]
    public GameObject turret;
    
    private Renderer rend;
    private Color startColor;

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
        TurretBlueprint blueprint = buildManager.GetTurretToBuild();
        if (!buildManager.CanBuild)
            return;

        if (turret != null)
        {
            Debug.Log("We can't build here!!");
            return;
        }

        // Build the turret using the prefab from the blueprint
        buildManager.BuildTurretOn(this);

        Debug.Log("Turret built! Cost: " + blueprint.cost);
    }


    void OnMouseEnter()
    {
        if (!buildManager.CanBuild)
            return;

        rend.material.color = hoverColor;
    }

    void OnMouseExit()
    {
        rend.material.color = startColor;
    }
}

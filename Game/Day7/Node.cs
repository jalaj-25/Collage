using UnityEngine;

public class Node : MonoBehaviour
{
    public Color hoverColor;
    private Renderer rend;
    private GameObject turret;
    private Color startColor;

    private BuildManager buildManager;

    void Start()
    {
        rend = GetComponent<Renderer>();
        startColor = rend.material.color;

        // ✅ Initialize buildManager
        buildManager = BuildManager.instance;
    }

    void OnMouseDown()
    {
        TurretBlueprint blueprint = buildManager.GetTurretToBuild();
        if (blueprint == null)
            return;

        if (turret != null)
        {
            Debug.Log("We can't build here!!");
            return;
        }

        // Build the turret using the prefab from the blueprint
        turret = Instantiate(blueprint.prefab, transform.position, transform.rotation);

        Debug.Log("Turret built! Cost: " + blueprint.cost);
    }


    void OnMouseEnter()
    {
        //if (EventSchema.current.IsPointerOverGameObject())
        //{
        //    return;
        //}
        if (buildManager.GetTurretToBuild() == null)
            return;

        rend.material.color = hoverColor;
    }

    void OnMouseExit()
    {
        rend.material.color = startColor;
    }
}

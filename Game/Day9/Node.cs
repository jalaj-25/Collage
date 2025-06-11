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

        if (!buildManager.CanBuild)
            return;

        if (turret != null)
        {
            Debug.Log("We can't build here!!");
            return;
        }

        buildManager.BuildTurretOn(this);
    }



    void OnMouseEnter()
    {
        if (EventSystem.current.IsPointerOverGameObject()) // ✅ Correct
        {
            return;
        }
        if (!buildManager.CanBuild)
            return;

        rend.material.color = hoverColor;
    }

    void OnMouseExit()
    {
        rend.material.color = startColor;
    }
}

using UnityEngine;

[System.Serializable]
public class TurretBlueprint
{
    public GameObject prefab;
    public int cost;

    public int upgradeCostToSilver;
    public GameObject upgradedPrefabToSilver;
    
    public int upgradeCostToGold;
    public GameObject upgradedPrefabToGold;

    public int GetSellAmount()
    {
        return cost / 2;
    }
}

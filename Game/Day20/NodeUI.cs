using UnityEngine;
using UnityEngine.UI;
using TMPro;

public class NodeUI : MonoBehaviour
{
    private Node target;
    public GameObject ui;

    public TextMeshProUGUI upgradeCostToSilver;
    public TextMeshProUGUI upgradeCostToGold;

    public Button upgradeButton;  // ✅ Correct name here

    public TextMeshProUGUI sellAmount;
    public Button sellButton;

    public void SetTarget(Node _target)
    {
        target = _target;
        transform.position = target.GetBuildPosition();

        // Handle upgrade levels:
        if (!target.isSilverUpgraded)
        {
            // Silver upgrade available
            upgradeCostToSilver.text = "$" + target.turretBlueprint.upgradeCostToSilver;
            upgradeCostToSilver.gameObject.SetActive(true);
            upgradeCostToGold.gameObject.SetActive(false);
            upgradeButton.interactable = true;
        }
        else if (target.isSilverUpgraded && !target.isGoldUpgraded)
        {
            // Gold upgrade available
            upgradeCostToSilver.gameObject.SetActive(false);
            upgradeCostToGold.gameObject.SetActive(true);
            upgradeCostToGold.text = "$" + target.turretBlueprint.upgradeCostToGold;
            upgradeButton.interactable = true;
        }
        else if (target.isGoldUpgraded)
        {
            // Fully upgraded
            upgradeCostToSilver.gameObject.SetActive(false);
            upgradeCostToGold.gameObject.SetActive(true);
            upgradeCostToGold.text = "Maxed";
            upgradeButton.interactable = false;
        }

        sellAmount.text = "$" + target.turretBlueprint.GetSellAmount();
        ui.SetActive(true);
    }

    public void Hide()
    {
        ui.SetActive(false);
    }

    public void Upgrade()
    {
        if (!target.isSilverUpgraded)
        {
            target.UpgradeToSilver();
        }
        else if (!target.isGoldUpgraded)
        {
            target.UpgradeToGold();
        }

        BuildManager.instance.DeselectNode();
    }

    public void Sell()
    {
        target.SellTurret();
        BuildManager.instance.DeselectNode();
    }
}

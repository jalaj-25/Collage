using UnityEngine;
using UnityEngine.UI;
using TMPro;

public class NodeUI : MonoBehaviour
{
    private Node target;
    public GameObject ui;

    public TextMeshProUGUI upgradeCost;
    public Button upgradedButton;

    public TextMeshProUGUI sellAmount;
    public Button sellButton;

    public void SetTarget(Node _target)
    {
        target = _target;

        transform.position = target.GetBuildPosition();

        if(!target.isUpgraded)
        {
            upgradeCost.text = "$" + target.turretBlueprint.upgradeCost;
            upgradedButton.interactable = true;
        } else
        {
            upgradedButton.interactable = false;
            upgradeCost.text = "Doner";
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
        target.UpgradeTurret();
        BuildManager.instance.DeselectNode();
    }

    public void Sell()
    {
        target.SellTurret();
        BuildManager.instance.DeselectNode();

    }
}

using UnityEngine;

public class CamController : MonoBehaviour
{
    private bool doMovement = true;
    public float panSpeed = 30f;
    public float panBoarderThickness = 10f;
    void Update()
    {
        if(Input.GetKeyDown(KeyCode.Escape))
        {
            doMovement = !doMovement;
        }
        if(!doMovement)
        {
            return;
        }
        if (Input.GetKey("w") || Input.mousePosition.y >= Screen.height - panBoarderThickness)
        {
            transform.Translate(Vector3.forward * panSpeed * Time.deltaTime, Space.World);
        }

        if (Input.GetKey("s") || Input.mousePosition.y <= panBoarderThickness) //backward
        {
            transform.Translate(Vector3.back * panSpeed * Time.deltaTime, Space.World);
        }

        if (Input.GetKey("d") || Input.mousePosition.x >= Screen.width - panBoarderThickness) //right
        {
            transform.Translate(Vector3.right * panSpeed * Time.deltaTime, Space.World);
        }

        if (Input.GetKey("a") || Input.mousePosition.x <= panBoarderThickness)
        {
            transform.Translate(Vector3.left * panSpeed * Time.deltaTime, Space.World);
        }


    }
}

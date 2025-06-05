using UnityEngine;

public class Bullet : MonoBehaviour
{
    public Transform target; // Corrected: Declare target as a public Transform
    public float speed = 10f; // Added: Declare speed for bullet movement

    public GameObject impactEffect; 

    // Update is called once per frame
    void Update()
    {
        if (target == null)
        {
            Destroy(gameObject);
            return;
        }

        Vector3 dir = target.position - transform.position;
        float distanceThisFrame = speed * Time.deltaTime;

        if (dir.magnitude <= distanceThisFrame)
        {
            HitTarget();
            return;
        }

        transform.Translate(dir.normalized * distanceThisFrame, Space.World);
    }

    void HitTarget()
    {
        GameObject effectIns = (GameObject)Instantiate(impactEffect, transform.position, transform.rotation);
        Destroy(effectIns, 2);
        Debug.Log("We hit something!!");
        Destroy(target.gameObject);
        Destroy(gameObject); // Added: Destroy bullet after hitting the target
    }

    public void Seek(Transform _target) // Corrected: Change 'puvblic' to 'public', and _transform to _target
    {
        target = _target;
    }
}

using System.Collections.Specialized;
using System.Diagnostics;
using System.Security.Permissions;
using UnityEngine;

public class Turret : MonoBehaviour
{
    public Transform target;
    [Header("Attributes")]

    public float fireRate = 1f;
    private float fireCountdown = 0f;

    public float range = 15f;

    [Header("Unity setup fields")]

    public string enemyTag = "Enemy";

    public Transform partToRotate;
    public float turnSpeed = 10f;

    public GameObject bulletPrefab;
    public Transform firePoint;
   

    void Start()
    {
        InvokeRepeating("UpdateTarget", 0f, 0.5f);
    }

    void UpdateTarget()
    {
        GameObject[] enemies = GameObject.FindGameObjectsWithTag(enemyTag);
        float shortestDistance = Mathf.Infinity;
        GameObject nearestEnemy = null;
            
        foreach (GameObject Enemy in enemies)
        {
            float distanceToEnemy = Vector3.Distance(transform.position, Enemy.transform.position);
            if (distanceToEnemy < shortestDistance)
            {
                shortestDistance = distanceToEnemy;
                nearestEnemy = Enemy;
            }
        }

        if (nearestEnemy != null && shortestDistance <= range)
        {
            target = nearestEnemy.transform;
        }
        else
        {
            target = null;
        }
    }
    void Update()
    {
        if (target == null)
            return;

        // Get direction to the target
        Vector3 dir = target.position - partToRotate.position;
        dir.y = 0f; // Lock rotation to Y-axis (no vertical rotation)

        if (dir == Vector3.zero)
            return;

        // Get target rotation and apply 90-degree offset
        Quaternion targetRotation = Quaternion.LookRotation(dir);
        Quaternion offsetRotation = targetRotation * Quaternion.Euler(0f,270f, 0f);

        // Smoothly rotate towards target with offset
        partToRotate.rotation = Quaternion.Slerp(
            partToRotate.rotation,
            offsetRotation,
            Time.deltaTime * turnSpeed
        );

        if(fireCountdown <= 0)
        {
            Shoot();
            fireCountdown = 1f / fireRate;  
        }
        fireCountdown -= Time.deltaTime;
    }

    void Shoot()
    {
        GameObject bulletGO = (GameObject)Instantiate(bulletPrefab, firePoint.position, firePoint.rotation);
        Bullet bullet = bulletGO.GetComponent<Bullet>();

        if(bullet != null )
        {
            bullet.Seek(target);
        }

        UnityEngine.Debug.Log("Shot!");
    }

    void OnDrawGizmosSelected() //boundry/ range of the bulllet 
    {
        Gizmos.color = Color.red;
        Gizmos.DrawWireSphere(transform.position, range);
    }
}

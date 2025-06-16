using System.Collections.Specialized;
using System.Diagnostics;
using System.Security.Permissions;
using UnityEngine;

public class Turret : MonoBehaviour
{
    public Transform target;
    [Header("Use Bullets")]

    public float fireRate = 1f;
    private float fireCountdown = 0f;
    public GameObject bulletPrefab;

    [Header("Use Laser")]
    public bool useLaser = false;
    public LineRenderer lineRenderer;
    public ParticleSystem impactEffect;

    [Header("Genral")]
    public float range = 15f;

    [Header("Unity setup fields")]

    public string enemyTag = "Enemy";

    public Transform partToRotate;
    public float turnSpeed = 10f;

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
        {
            if(useLaser)
            {
                if(lineRenderer.enabled)
                {
                    lineRenderer.enabled = false;
                    impactEffect.Stop();
                } 
            }
            return;

        }

        LockOnTarget();

        if (useLaser)
        {
            Laser(); // ✅ Fixed here
        }
        else
        {
            if (fireCountdown <= 0)
            {
                Shoot();
                fireCountdown = 1f / fireRate;
            }
            fireCountdown -= Time.deltaTime;
        }
    }
    void LockOnTarget()
    {
        // Get direction to the target
        Vector3 dir = target.position - partToRotate.position;
        dir.y = 0f; // Lock rotation to Y-axis (no vertical rotation)

        if (dir == Vector3.zero)
            return;

        // Get target rotation and apply 90-degree offset
        Quaternion targetRotation = Quaternion.LookRotation(dir);
        Quaternion offsetRotation = targetRotation * Quaternion.Euler(0f, 270f, 0f);

        // Smoothly rotate towards target with offset
        partToRotate.rotation = Quaternion.Slerp(
            partToRotate.rotation,
            offsetRotation,
            Time.deltaTime * turnSpeed
        );
    }

    void Laser()
    {
        if (!lineRenderer.enabled)
        {
            lineRenderer.enabled = true;
            impactEffect.Play();
        }


        lineRenderer.SetPosition(0, firePoint.position);
        lineRenderer.SetPosition(1, target.position); // ✅ Fixed index

        Vector3 dir = firePoint.position - transform.position;

        impactEffect.transform.rotation = Quaternion.LookRotation(dir);

        impactEffect.transform.position = target.position + dir.normalized * .5f;
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

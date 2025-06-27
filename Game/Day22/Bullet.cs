using UnityEngine;

public class Bullet : MonoBehaviour
{
    public Transform target;
    public float speed = 10f;
    public float explosionRadius = 0f;
    public GameObject impactEffect;
    public int damage = 50;

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
        transform.LookAt(target);
    }

    void HitTarget()
    {
        GameObject effectIns = Instantiate(impactEffect, transform.position, transform.rotation);
        Destroy(effectIns, 5f);
        Debug.Log("We hit something!!");

        if (explosionRadius > 0f)
        {
            Explode();
        }
        else
        {
            Damage(target);
        }

        Destroy(gameObject);
    }

    void Explode()
    {
        Collider[] colliders = Physics.OverlapSphere(transform.position, explosionRadius);
        foreach (Collider collider in colliders)
        {
            if (collider.CompareTag("Enemy"))
            {
                Damage(collider.transform);
            }
        }
    }

    void Damage(Transform enemyGo)
    {
        Enemy e = enemyGo.GetComponent<Enemy>(); // ✅ fixed: enemyGo, not enemy
        if (e != null)
        {
            e.TakeDamage(damage); // ✅ fixed: TakeDamage with capital D
        }
    }

    public void Seek(Transform _target)
    {
        target = _target;
    }

    void OnDrawGizmosSelected()
    {
        Gizmos.color = Color.red;
        Gizmos.DrawWireSphere(transform.position, explosionRadius);
    }
}

using UnityEngine;

public class Enemy : MonoBehaviour
{
    [HideInInspector]
    public float speed;
    public float health = 100f;
    public int worth = 50;
    public GameObject DeathEffect;
    public float startSpeed = 10f;

    void Start()
    {
        speed = startSpeed;  // Initialize speed when enemy spawns
    }

    public void TakeDamage(float amount)
    {
        health -= amount;
        if (health <= 0)
        {
            Die();
        }
    }

    public void Slow(float pct)
    {
        float newSpeed = startSpeed * (1f - pct);
        if (speed > newSpeed)
        {
            speed = newSpeed;
        }
    }

    void Die()
    {
        PlayerStats.Money += worth;
        GameObject effect = Instantiate(DeathEffect, transform.position, transform.rotation);
        Destroy(effect, 5f);
        Destroy(gameObject);
    }
}

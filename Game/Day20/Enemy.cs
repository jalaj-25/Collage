using UnityEngine;
using UnityEngine.UI;

public class Enemy : MonoBehaviour
{
    [HideInInspector]
    public float speed;

    public float startSpeed = 10f;

    private float startHealth = 100f;

    public float health = 100f;

    public int worth = 50;

    public GameObject DeathEffect;

    [Header("Heath bar")]
    public Image HealthBar;

    void Start()
    {
        speed = startSpeed;  // Initialize speed when enemy spawns
        health = startHealth;
    }

    public void TakeDamage(float amount)
    {
        health -= amount;
        
        HealthBar.fillAmount = health / startHealth;

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
        WaveSpawner.EnemiesAlive--;
    }
}

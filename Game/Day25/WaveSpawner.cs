using System.Collections;
using TMPro;
using UnityEngine;

public class WaveSpawner : MonoBehaviour
{
    public static int EnemiesAlive = 0;

    public Wave[] waves;
    public Transform spawnPoint;
    public GameManager gameManger;
    public TextMeshProUGUI waveCountdownText;

    public float timeBetweenWaves = 5f;
    private float countDown = 2f;
    private int waveIndex = 0;

    void Update()
    {
        if (EnemiesAlive > 0)
            return;

        if (waveIndex == waves.Length)
        {
            gameManger.WinLevel();
            enabled = false;
            return;
        }

        if (countDown <= 0f)
        {
            StartCoroutine(SpawnWave());
            countDown = timeBetweenWaves;
            return;
        }

        countDown -= Time.deltaTime;
        countDown = Mathf.Clamp(countDown, 0f, Mathf.Infinity);

        waveCountdownText.text = string.Format("{0:00.00}", countDown);
    }

    IEnumerator SpawnWave()
    {
        PlayerStats.Rounds++;
        Wave wave = waves[waveIndex];
        EnemiesAlive = wave.count;

        for (int i = 0; i < wave.count; i++)
        {
            SpawnEnemy(wave.enemy);
            float delay = wave.rate <= 0 ? 1f : 1f / wave.rate;
            yield return new WaitForSeconds(delay);
        }

        waveIndex++;
    }

    void SpawnEnemy(GameObject enemy)
    {
        if (enemy == null)
        {
            Debug.LogError("Enemy prefab is missing or was destroyed!");
            return;
        }
        Instantiate(enemy, spawnPoint.position, spawnPoint.rotation);
    }
}

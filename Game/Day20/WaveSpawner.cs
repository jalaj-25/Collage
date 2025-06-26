using System;
using System.Collections;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class WaveSpawner : MonoBehaviour
{
    public static int EnemiesAlive = 0;
    public Wave[] waves;
    public Transform enemyPrefab;
    public Transform spawnPoint;
    public float timeBetweenWaves = 5f;
    private float countDown = 2f;
    private int waveIndex = 0;
    public GameManager gameManger;

    public TextMeshProUGUI waveCountdownText;

    void Update()
    {
        if(EnemiesAlive > 0)
        {
            return;
        }
        if (countDown <= 0f)
        {
            StartCoroutine(SpawnWave());  // ✅ Correct coroutine call
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

        for (int i = 0; i < wave.count; i++)  // ✅ Correct variable name
        {
            SpawnEnemy(wave.enemy);
            yield return new WaitForSeconds(1/wave.rate);  // ✅ Correct casing
        }
        waveIndex++;

        if(waveIndex == waves.Length)
        {
            gameManger.WinLevel();
            this.enabled = false;
        }
    }

    void SpawnEnemy(GameObject enemy)
    {
        Instantiate(enemy, spawnPoint.position, spawnPoint.rotation);
    }
}

using System;
using System.Collections;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class WaveSpawner : MonoBehaviour
{
    public Transform enemyPrefab;
    public Transform spawnPoint;
    public float timeBetweenWaves = 5f;
    private float countDown = 2f;
    private int waveIndex = 0;

    public TextMeshProUGUI waveCountdownText;

    void Update()
    {
        if (countDown <= 0f)
        {
            StartCoroutine(SpawnWave());  // ✅ Correct coroutine call
            countDown = timeBetweenWaves;
        }

        countDown -= Time.deltaTime;
        countDown = Mathf.Clamp(countDown, 0f, Mathf.Infinity);

        waveCountdownText.text = string.Format("{0:00.00}", countDown);
    }

    IEnumerator SpawnWave()
    {
        waveIndex++;
        for (int i = 0; i < waveIndex; i++)  // ✅ Correct variable name
        {
            SpawnEnemy();
            yield return new WaitForSeconds(0.5f);  // ✅ Correct casing
        }

        Debug.Log("Enemy Ahead!!");
        Debug.Log("Wave incoming!!");
    }

    void SpawnEnemy()
    {
        Instantiate(enemyPrefab, spawnPoint.position, spawnPoint.rotation);
    }
}

using UnityEngine;
using System.Collections.Generic;
using TMPro;

public class snake : MonoBehaviour
{
    private Vector2 _direction = Vector2.right;
    private List<Transform> _segments = new List<Transform>();
    public Transform segmentPrefab;
    public GameOverMenu gameOverMenu;
    public MainMenu mainMenu;
    public TextMeshProUGUI segmentCountText;
    public GameObject GameOverUI;
    public AudioClip deathSound;
    public AudioClip NormalFoodSound;
    public AudioClip SuperFoodSound;
    public ScoreUIManager scoreUIManager;

    private void Start()
    {
        ResetState();
    }
    private void Update()
    {
        if (Input.GetKeyDown(KeyCode.W))
        {
            _direction = Vector2.up;
        }
        else if (Input.GetKeyDown(KeyCode.S))
        {
            _direction = Vector2.down;
        }
        else if (Input.GetKeyDown(KeyCode.A))
        {
            _direction = Vector2.left;
        }
        else if (Input.GetKeyDown(KeyCode.D))
        {
            _direction = Vector2.right;
        }
    }

    private void FixedUpdate()
    {
        for (int i = _segments.Count - 1; i > 0; i--)
        {
            _segments[i].position = _segments[i - 1].position;
        }
        this.transform.position = new Vector3(
            Mathf.Round(this.transform.position.x) + _direction.x,
            Mathf.Round(this.transform.position.y) + _direction.y,
            0.0f
        );
    }

    private void Grow()
    {
        Transform segment = Instantiate(this.segmentPrefab);
        segment.position = _segments[_segments.Count - 1].position;
        _segments.Add(segment);
        scoreUIManager.UpdateCurrentScore(_segments.Count);
        UpdateSegmentCountUI();
    }

    private void UpdateSegmentCountUI()
    {
        segmentCountText.text = _segments.Count.ToString();
    }

    private void ResetState()
    {
        for (int i = 1; i < _segments.Count; i++)
        {
            Destroy(_segments[i].gameObject);
        }
        _segments.Clear();
        _segments.Add(this.transform);
        this.transform.position = Vector3.zero;
        scoreUIManager.ResetScores();
    }

    private void PlayDeathSound()
    {
        if (deathSound != null)
        {
            AudioSource.PlayClipAtPoint(deathSound, transform.position);
        }
    }
    
    private void PlayFoodSound(AudioClip clip)
    {
        if (NormalFoodSound != null)
        {
            AudioSource.PlayClipAtPoint(clip, transform.position);
        }
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.tag == "Food")
        {
            Food food = other.GetComponent<Food>();

            if (food != null)
            {
                switch (food.type)
                {
                    case FoodType.Normal:
                        Grow();
                        PlayFoodSound(NormalFoodSound);
                        break;
                    case FoodType.Super:
                        Grow();
                        Grow();
                        PlayFoodSound(SuperFoodSound);
                        break;
                    case FoodType.Poison:
                        if (_segments.Count > 1)
                        {
                            Destroy(_segments[_segments.Count - 1].gameObject);
                            _segments.RemoveAt(_segments.Count - 1);
                            UpdateSegmentCountUI();
                        }
                        else if (_segments.Count == 1)
                        {
                            PlayDeathSound();
                            GameOverUI.SetActive(true);
                            Time.timeScale = 0f;
                        }
                        break;
                }
            }
        }
        else if (other.tag == "obestacle" || other.tag == "Player")
        {
            PlayDeathSound();
            gameOverMenu.ShowGameOver();
        }
    }
}



using UnityEngine;

public enum FoodType
{
    Normal,
    Super,
    Poison
}

public class Food : MonoBehaviour
{
    public BoxCollider2D gridArea;
    public FoodType type;

    private void Start()
    {
        RandomizeBounds();

        SpriteRenderer sr = GetComponent<SpriteRenderer>();
        switch (type)
        {
            case FoodType.Normal:
                sr.color = Color.red;
                break;
            case FoodType.Super:
                sr.color = Color.yellow;
                break;
            case FoodType.Poison:
                sr.color = Color.black;
                break;
        }
    }

    private void RandomizeBounds()
    {
        Bounds bounds = this.gridArea.bounds;

        float x = Random.Range(bounds.min.x, bounds.max.x);
        float y = Random.Range(bounds.min.y, bounds.max.y);

        this.transform.position = new Vector3(Mathf.Round(x), Mathf.Round(y), 0.0f);
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if(other.tag == "Player")
        {
            RandomizeBounds();

        }
    }
}

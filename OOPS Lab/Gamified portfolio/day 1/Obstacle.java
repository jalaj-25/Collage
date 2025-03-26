//float roadHeight = 300; // Define roadHeight globally

class Obstacle {
  float x, y, size, speed;

  Obstacle() {
    this.x = random(width / 2 - roadHeight / 2, width / 2 + roadHeight / 2);
    this.y = -50;
    this.size = random(40, 80);
    this.speed = 5;
  }

  void move() {
    this.y += this.speed;
  }

  void display() {
    fill(255, 0, 0);
    ellipse(this.x, this.y, this.size, this.size);
  }

  boolean hits(float cartX, float cartY, float cartW, float cartH) {
    return (this.x + this.size / 2 > cartX && this.x - this.size / 2 < cartX + cartW &&
            this.y + this.size / 2 > cartY && this.y - this.size / 2 < cartY + cartH);
  }
}

// ✅ Cart movement + rotation with diagonal support
void moveCart() {
    float roadLeft = width / 2 - roadHeight / 2;
    float roadRight = width / 2 + roadHeight / 2;

    float dx = 0, dy = 0; // Store movement in x and y directions

    if (moveLeft)  dx -= speed;
    if (moveRight) dx += speed;
    if (moveUp)    dy -= speed;
    if (moveDown)  dy += speed;

    // Normalize diagonal speed (so it's not faster)
    if (dx != 0 && dy != 0) {
        dx *= 0.7;
        dy *= 0.7;
    }

    float newX = cart_x + dx;
    float newY = cart_y + dy;

    if (newX >= roadLeft && newX + cart.width <= roadRight) {
        cart_x = newX;
    }
    if (newY >= 0 && newY + cart.height <= height) {
        cart_y = newY;
    }

    // Set rotation angle based on movement
    if (dx > 0 && dy < 0)       rotationAngle = 45;  // Up-right
    else if (dx > 0 && dy > 0)  rotationAngle = 135; // Down-right
    else if (dx < 0 && dy > 0)  rotationAngle = 225; // Down-left
    else if (dx < 0 && dy < 0)  rotationAngle = 315; // Up-left
    else if (dx > 0)            rotationAngle = 90;  // Right
    else if (dx < 0)            rotationAngle = 270; // Left
    else if (dy < 0)            rotationAngle = 0;   // Up
    else if (dy > 0)            rotationAngle = 180; // Down

    // Draw cart with rotation
    pushMatrix();
    translate(cart_x + cart.width / 2, cart_y + cart.height / 2);
    rotate(radians(rotationAngle));
    imageMode(CENTER);
    image(cart, 0, 0);
    popMatrix();
}

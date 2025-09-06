class AchievementBlock {
    float x, y, w, h;
    float targetX, targetY, targetW, targetH;
    boolean hovered = false;  // Track hover state
    boolean showBackButton = false; // Track Back button visibility

    AchievementBlock(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        // Default position (at the right side)
        this.targetX = width - w - 50;
        this.targetY = 50;
        this.targetW = w;
        this.targetH = h;
    }

    void display() {
        // Smooth transition for position and size
        x = lerp(x, targetX, 0.1);
        y = lerp(y, targetY, 0.1);
        w = lerp(w, targetW, 0.1);
        h = lerp(h, targetH, 0.1);

        if (hovered) {
            // Full-screen effect when hovered
            background(255, 165, 0); // Orange background
            fill(0);
            textAlign(CENTER, CENTER);
            textSize(48);
            text("ACHIEVEMENTS UNLOCKED!", width / 2, height / 4);

            textSize(32);
            text("• Game Mastery", width / 2, height / 2 - 50);
            text("• 10+ Projects Completed", width / 2, height / 2);
            text("• Coding Ninja!", width / 2, height / 2 + 50);

            // Show Back button
            showBackButton = true;
            drawBackButton();
        } else {
            // Draw the achievement box
            fill(255, 165, 0);
            rect(x, y, w, h, 15);

            fill(0);
            textAlign(CENTER, CENTER);
            textSize(24);
            text("Achievement", x + w / 2, y + h / 2);

            showBackButton = false; // Hide back button in normal mode
        }
    }

    void checkOverlap(float cartX, float cartY, float cartW, float cartH) {
        if (cartX + cartW > x && cartX < x + w &&
            cartY + cartH > y && cartY < y + h) {
            // When overlapped, expand to 80% of the screen size
            hovered = true;
            targetW = width * 0.8;
            targetH = height * 0.8;
            targetX = (width - targetW) / 2;
            targetY = (height - targetH) / 2;
        } 

        // Check if cart overlaps the Back button (LARGER SIZE: 250x80)
        if (showBackButton && cartX + cartW > width / 2 - 125 &&
            cartX < width / 2 + 125 &&
            cartY + cartH > height - 120 &&
            cartY < height - 40) {
            resetSize(); // Reset size when cart hovers over Back button
        }
    }

    void resetSize() {
        hovered = false;
        targetW = 300; // Original width
        targetH = 300; // Original height
        targetX = width - targetW - 50;
        targetY = 50;
    }

    void drawBackButton() {
        fill(0);
        rect(width / 2 - 125, height - 120, 250, 80, 15); // Enlarged button (250x80)

        fill(255);
        textAlign(CENTER, CENTER);
        textSize(28);
        text("Back", width / 2, height - 80);
    }
}

// ✅ Enhanced Achievement block with full-screen effect
class AchievementBlock {
    float x, y, w, h;
    float targetW, targetH;
    boolean hovered = false;  // Track hover state

    AchievementBlock(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.targetW = w;
        this.targetH = h;
    }

    void display() {
        if (hovered) {
            // ✅ Make background fully orange
            background(255, 165, 0);

            // ✅ Display the achievements text
            fill(0);
            textAlign(CENTER, CENTER);
            textSize(48);
            text("ACHIEVEMENTS UNLOCKED!", width / 2, height / 4);

            textSize(32);
            text("• Game Mastery", width / 2, height / 2 - 50);
            text("• 10+ Projects Completed", width / 2, height / 2);
            text("• Coding Ninja!", width / 2, height / 2 + 50);
        } else {
            // Smoothly transition size changes
            w = lerp(w, targetW, 0.1);
            h = lerp(h, targetH, 0.1);

            // ✅ Positioned at the extreme right
            x = width - w - 50;
            y = 50;

            // ✅ Draw the main rectangle
            fill(255, 165, 0);  // Orange color
            rect(x, y, w, h, 15);

            // ✅ Default text
            fill(0);
            textAlign(CENTER, CENTER);
            textSize(24);
            text("Achievement", x + w / 2, y + h / 2);
        }
    }

    void checkOverlap(float cartX, float cartY, float cartW, float cartH) {
        if (cartX + cartW > x && cartX < x + w &&
            cartY + cartH > y && cartY < y + h) {
            // ✅ Full-screen takeover on hover
            hideBackground = true;
            hovered = true;
        } else {
            // ✅ Reset to normal view
            hideBackground = false;
            hovered = false;
        }
    }
}

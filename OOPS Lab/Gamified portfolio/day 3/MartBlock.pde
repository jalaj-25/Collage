// ✅ Mart Block class with full-screen hover effect
class MartBlock {
    float x, y, w, h;
    float targetW, targetH;
    boolean hovered = false;  // Track hover state

    // ✅ Constructor
    MartBlock(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.targetW = w;
        this.targetH = h;
    }

    void display() {
        if (hovered) {
            // ✅ Full-screen blue background when hovered
            background(0, 102, 204);

            // ✅ Display Mart-related information
            fill(255);
            textAlign(CENTER, CENTER);
            textSize(48);
            text("WELCOME TO THE MART!", width / 2, height / 4);

            textSize(32);
            text("• Fresh Groceries", width / 2, height / 2 - 50);
            text("• Exclusive Discounts", width / 2, height / 2);
            text("• Shop Now & Save!", width / 2, height / 2 + 50);
        } else {
            // ✅ Smooth transition for expansion
            w = lerp(w, targetW, 0.1);
            h = lerp(h, targetH, 0.1);

            // ✅ Draw the rectangle
            fill(0, 102, 204);  // Blue color
            rect(x, y, w, h, 15);

            // ✅ Draw text inside the rectangle
            fill(255);
            textSize(32);
            textAlign(CENTER, CENTER);
            text("MART", x + w / 2, y + h / 2);
        }
    }

    // ✅ Check if cart overlaps and apply effects
    void checkOverlap(float cartX, float cartY, float cartW, float cartH) {
        if (cartX + cartW > x && cartX < x + w &&
            cartY + cartH > y && cartY < y + h) {
            // ✅ Full-screen takeover on hover
            hovered = true;
        } else {
            // ✅ Reset to normal view
            hovered = false;
        }
    }
}

class ProfileBlock {
    float x, y, w, h;
    float targetX, targetY, targetW, targetH;
    boolean hovered = false; 
    boolean showBackButton = false; 

    ProfileBlock(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.targetX = (width - w) / 2;
        this.targetY = y;
        this.targetW = w;
        this.targetH = h;
    }

    void display() {
        x = lerp(x, targetX, 0.1);
        y = lerp(y, targetY, 0.1);
        w = lerp(w, targetW, 0.1);
        h = lerp(h, targetH, 0.1);

        if (hovered) {
            hideBackground = true;
            background(255, 150, 100); // Orange-ish background
            fill(255);
            textAlign(CENTER, CENTER);
            textSize(48);
            text("MY PROFILE", width / 2, height / 4);

            textSize(32);
            text("Name: Jalaj", width / 2, height / 2 - 50);
            text("Specialization: GG", width / 2, height / 2);
            text("CSR Head | ACM-W", width / 2, height / 2 + 50);

            showBackButton = true;
            drawBackButton();
        } else {
            fill(255, 150, 100);
            rect(x, y, w, h, 15);

            fill(0);
            textAlign(CENTER, CENTER);
            textSize(32);
            text("Profile", x + w / 2, y + h / 2);

            showBackButton = false;
        }
    }

    void checkOverlap(float cartX, float cartY, float cartW, float cartH) {
        if (cartX + cartW > x && cartX < x + w &&
            cartY + cartH > y && cartY < y + h) {
            hovered = true;
            targetW = width * 0.8;
            targetH = height * 0.8;
            targetX = (width - targetW) / 2;
            targetY = (height - targetH) / 2;
        }

        if (showBackButton && cartX + cartW > width / 2 - 125 &&
            cartX < width / 2 + 125 &&
            cartY + cartH > height - 120 &&
            cartY < height - 40) {
            resetSize();
        }
    }

    void resetSize() {
        hovered = false;
        hideBackground = false;
        targetW = 300;
        targetH = 300;
        targetX = (width - targetW) / 2;
        targetY = y; // Reset to original Y
    }

    void drawBackButton() {
        fill(0);
        rect(width / 2 - 125, height - 120, 250, 80, 15);

        fill(255);
        textAlign(CENTER, CENTER);
        textSize(28);
        text("Back", width / 2, height - 80);
    }
}
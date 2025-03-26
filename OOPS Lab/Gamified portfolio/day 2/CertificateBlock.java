// ✅ Certificate Block class with full-screen hover effect
class CertificateBlock {
    float x, y, w, h;
    float targetW, targetH;
    boolean hovered = false; // Track hover state

    // ✅ Constructor accepting x, y, w, h
    CertificateBlock(float x, float y, float w, float h) {
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
            background(100, 100, 255);

            // ✅ Display Certificate-related information
            fill(255);
            textAlign(CENTER, CENTER);
            textSize(48);
            text("MY CERTIFICATES", width / 2, height / 4);

            textSize(32);
            text("• Professional Achievements", width / 2, height / 2 - 50);
            text("• Skill Certifications", width / 2, height / 2);
            text("• Awards & Recognitions", width / 2, height / 2 + 50);
        } else {
            // ✅ Smoothly transition size changes
            w = lerp(w, targetW, 0.1);
            h = lerp(h, targetH, 0.1);

            // ✅ Keep it centered horizontally and add padding from top
            x = (width - w) / 2;
            y = 50; // 50px padding from top

            // ✅ Draw the rectangle
            fill(100, 100, 255);
            rect(x, y, w, h, 15);

            // ✅ Draw text inside the rectangle
            fill(255);
            textAlign(CENTER, CENTER);
            textSize(32);
            text("Certificates", x + w / 2, y + h / 2);
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

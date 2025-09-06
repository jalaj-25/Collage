import java.util.ArrayList;
import javax.swing.JOptionPane; // Import for password input

PImage cart; // Image for the cart
float cart_x, cart_y; // Cart position
float speed = 5; // Speed of cart
float maxBoxH = 4100, maxBoxW = 7000, minBox = 300; // Enlarged size of boxes
float difficultyMultiplier = 1.0; // Obstacle falling
float rotationAngle = 0; // Angle for rotation
float button_x, button_y, button_w = 180, button_h = 70; // Buttons size 
float git_button_y, mail_button_y;
float box_x, box_y, box_w, box_h; // Original box size
float pole_x, pole_y, pole_z; // Pole 3D model
float roadWidth, roadHeight; // Road size

boolean gameStarted = false, expandingBox = false; // Game and box state
boolean moveLeft = false, moveRight = false, moveUp = false, moveDown = false; // Cart movement condition
boolean hideBackground = false; // Background hiding
boolean obstaclesFalling = false; // Obstacles falling or not

int startTime = 0; // Game start time

ArrayList<Obstacle> obstacles = new ArrayList<>(); // Obstacle array

// Overlap condition
CertificateBlock certificateBlock; 
AchievementBlock achievementBlock; 
MartBlock martBlock;
ProfileBlock profileBlock;

boolean achievementUnlocked = false; // Password-protected achievement status

void setup() {
    fullScreen(P3D);  // Enable 3D rendering

    // Screen border
    stroke(#D60DFF);
    strokeWeight(7);
    pole_x = width / 2;
    pole_y = height / 2;
    pole_z = 0;

    // Load cart image
    cart = loadImage("cart.png");
    if (cart == null) {
        println("Failed to load cart image.");
        exit();
    }
    cart.resize(150, 120);

    // Cart position
    cart_x = width / 2;
    cart_y = height / 2;

    // Button positions
    button_x = 50;
    button_y = height - 120;
    mail_button_y = button_y - 90;
    git_button_y = mail_button_y - 90;

    // Box conditions
    box_w = minBox;
    box_h = minBox;
    box_x = 50;
    box_y = 50;

    // Road size
    roadWidth = width / 5;
    roadHeight = width;

    // Certificate block
    certificateBlock = new CertificateBlock(width / 2 - 150, height / 2 - 150, 300, 300);
  
    // Achievement block  
    achievementBlock = new AchievementBlock(width - 350, height / 2 - 150, 300, 300);
  
    // Mart block
    martBlock = new MartBlock(width - 350, height / 2 + 375, 300, 300);
    
    //profile block
    profileBlock = new ProfileBlock(width - 350, height / 2 + 375, 300, 300);
}

void draw() {
    if (!hideBackground) {
        background(#21EA73); // Background color

        // Draw road
        fill(0);
        pushMatrix();
        translate(width / 2, height / 2);
        rotate(PI);
        rect(-roadHeight / 2, -roadWidth / 2, roadHeight, roadWidth);
        popMatrix();

        // "PLAYGROUND" text
        fill(255);
        textSize(40);
        textAlign(CENTER, CENTER);
        text("PLAYGROUND", box_x + box_w / 2, box_y - 30);

        drawButtons();

        // Obstacles logic
        if (gameStarted && millis() - startTime > 2000) {
            obstaclesFalling = true;
            difficultyMultiplier += 0.002;

            if (frameCount % max(1, int(30 / difficultyMultiplier)) == 0) {
                obstacles.add(new Obstacle());
            }
        }

        if (obstaclesFalling) {
            for (int i = obstacles.size() - 1; i >= 0; i--) {
                obstacles.get(i).speed = 5 * difficultyMultiplier;
                obstacles.get(i).move();
                obstacles.get(i).display();

                if (obstacles.get(i).hits(cart_x, cart_y, cart.width, cart.height)) {
                    fill(255, 0, 0);
                    textSize(80);
                    textAlign(CENTER, CENTER);
                    text("GAME OVER!", width / 2, height / 2);
                    noLoop();
                }

                if (obstacles.get(i).y > height) {
                    obstacles.remove(i);
                }
            }
        }
    } else {
        background(100, 100, 255);
        certificateBlock.display();
    }

    moveCart();

    // Expand the box only if the Achievement Block is unlocked
    if (achievementUnlocked) {
        box_w = lerp(box_w, maxBoxW, 0.05);
        box_h = lerp(box_h, maxBoxH, 0.05);
    } else {
        box_w = lerp(box_w, minBox, 0.05);
        box_h = lerp(box_h, minBox, 0.05);
    }

    fill(255, 0, 0, 150);
    rect(box_x, box_y, box_w, box_h, 10);

    if (gameStarted) {
        fill(255);
        textSize(50);
        textAlign(CENTER, CENTER);
        text("Protect yourself from obstacles!", width / 2, 100);
    }

    // Certificate logic
    certificateBlock.checkOverlap(cart_x, cart_y, cart.width, cart.height);
    certificateBlock.display();

    // Achievement Block (Password Protected)
    achievementBlock.checkOverlap(cart_x, cart_y, cart.width, cart.height);
    achievementBlock.display();

    //mart block
    martBlock.checkOverlap(cart_x, cart_y, cart.width, cart.height);
    martBlock.display();
    
    //profile block
    profileBlock.checkOverlap(cart_x, cart_y, cart.width, cart.height);
    profileBlock.display();

    // Mart Box
    float mart_x = width - 350;
    float mart_y = height - 350;
    float mart_w = 300, mart_h = 300;

    fill(0, 102, 204);
    rect(mart_x, mart_y, mart_w, mart_h, 15);

    fill(255);
    textSize(32);
    textAlign(CENTER, CENTER);
    text("MART", mart_x + mart_w / 2, mart_y + mart_h / 2);

    moveCart();
}

void drawButtons() {
    fill(#24292F);
    rect(button_x, git_button_y, button_w, button_h, 15);
    fill(255);
    textSize(24);
    text("GitHub", button_x + button_w / 2, git_button_y + button_h / 2);

    fill(#4285F4);
    rect(button_x, mail_button_y, button_w, button_h, 15);
    fill(255);
    text("Send Email", button_x + button_w / 2, mail_button_y + button_h / 2);

    fill(#FF5733);
    rect(button_x, button_y, button_w, button_h, 15);
    fill(255);
    text("Visit LinkedIn", button_x + button_w / 2, button_y + button_h / 2);
}

void keyPressed() {
    if (key == 'a') moveLeft = true;
    if (key == 'd') moveRight = true;
    if (key == 'w') moveUp = true;
    if (key == 's') moveDown = true;
}

void keyReleased() {
    moveLeft = moveRight = moveUp = moveDown = false;
}

// Password Prompt Function
String promptPassword() {
    return JOptionPane.showInputDialog("Enter Password to Access Achievements:");
}

// Check Overlap for Achievement Block
void checkAchievementAccess() {
    boolean inside = (cart_x + cart.width > achievementBlock.x &&
                      cart_x < achievementBlock.x + achievementBlock.w &&
                      cart_y + cart.height > achievementBlock.y &&
                      cart_y < achievementBlock.y + achievementBlock.h);

    if (inside && !achievementUnlocked) {
        String inputPassword = promptPassword();
        if (inputPassword.equals("1234")) {
            achievementUnlocked = true;
        } else {
            println("Incorrect Password. Access Denied!");
        }
    }
}
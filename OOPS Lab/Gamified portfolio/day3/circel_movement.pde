PImage cart; // Image for the cart
float cart_x, cart_y; //Cart postion
float speed = 5; //speed of cart
float maxBoxH = 4100, maxBoxW = 7000, minBox = 300; //enlarged size of boxes
float difficultyMultiplier = 1.0; //obstacle falling
float rotationAngle = 0; // Angle for rotation
float button_x, button_y, button_w = 180, button_h = 70; //buttons size 
float git_button_y, mail_button_y;
float box_x, box_y, box_w, box_h; //orignal box size
float pole_x, pole_y, pole_z; //pole 3d model
float roadWidth, roadHeight; //road size

boolean gameStarted = false, expandingBox = false; // game and box 
boolean moveLeft = false, moveRight = false, moveUp = false, moveDown = false; //cart movement condition
boolean hideBackground = false; //bg is hiding
boolean obstaclesFalling = false; // obstacles are falling or not

int startTime = 0; //game started time

ArrayList<Obstacle> obstacles = new ArrayList<>(); //obstaclle array

//overlap condition
CertificateBlock certificateBlock; 
AchievementBlock achievementBlock; 
MartBlock martBlock;

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
}


void draw() {
    if (!hideBackground) { //bg condition
        background(#21EA73); //bg colour

        // Draw road-like structure
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
        background(100, 100, 255); // Blue background when certificate is hovered
        certificateBlock.display(); // Display only the certificate block
    }

    // Ensure cart is always visible
    moveCart();
    
    if (!hideBackground) {
        // Expand/shrink playground box
        if (expandingBox) {
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

        // Expand playground when cart is inside
        boolean insidePlayground = (cart_x >= box_x && cart_x + cart.width <= box_x + box_w &&
                                    cart_y >= box_y && cart_y + cart.height <= box_y + box_h);

        if (insidePlayground) {
            box_w = lerp(box_w, maxBoxW, 0.02);
            box_h = lerp(box_h, maxBoxH, 0.02);
        } else {
            box_w = lerp(box_w, minBox, 0.02);
            box_h = lerp(box_h, minBox, 0.02);
        }

        achievementBlock.checkOverlap(cart_x, cart_y, cart.width, cart.height);
        achievementBlock.display();
        
        martBlock.checkOverlap(cart_x, cart_y, cart.width, cart.height);
        martBlock.display();

        // ✅ Draw Mart Box (Bottom Right)
        float mart_x = width - 350;
        float mart_y = height - 350;
        float mart_w = 300, mart_h = 300;

        fill(0, 102, 204);  // Blue color
        rect(mart_x, mart_y, mart_w, mart_h, 15);

        fill(255);
        textSize(32);
        textAlign(CENTER, CENTER);
        text("MART", mart_x + mart_w / 2, mart_y + mart_h / 2);
    }

    // Ensure cart is drawn last so it appears on top
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

void mousePressed() {
    // Check if clicking inside the back button
    if (certificateBlock.showBackButton &&
        mouseX > width / 2 - 125 && mouseX < width / 2 + 125 &&
        mouseY > height - 120 && mouseY < height - 40) {
        certificateBlock.resetSize();
    }
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

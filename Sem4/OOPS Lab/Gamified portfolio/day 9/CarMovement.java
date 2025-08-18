import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CarMovement {
    private JLabel carLabel;
    private double rotationAngle = 0;
    private static final int MOVE_AMOUNT = 20;

    public CarMovement(JLabel carLabel) {
        this.carLabel = carLabel;
    }

    public void handleKeyPress(KeyEvent e, JLayeredPane layeredPane) {
        int key = e.getKeyCode();

        // Handle car movement
        if (key == KeyEvent.VK_W) { // Move Up
            carLabel.setLocation(carLabel.getX(), carLabel.getY() - MOVE_AMOUNT);
        } else if (key == KeyEvent.VK_S) { // Move Down
            carLabel.setLocation(carLabel.getX(), carLabel.getY() + MOVE_AMOUNT);
        } else if (key == KeyEvent.VK_A) { // Move Left
            carLabel.setLocation(carLabel.getX() - MOVE_AMOUNT, carLabel.getY());
            rotationAngle = 180;  // Rotate car to 180 degrees when moving left
        } else if (key == KeyEvent.VK_D) { // Move Right
            carLabel.setLocation(carLabel.getX() + MOVE_AMOUNT, carLabel.getY());
            rotationAngle = 0; // Rotate car to 0 degrees when moving right
        }

        // Handle car image rotation based on the key pressed
        if (key == KeyEvent.VK_S) { // Press 'S' for 270 degree rotation
            rotationAngle = 270;
        }

        // Repaint the car label to apply the rotation
        repaint(layeredPane);
    }

    private void repaint(JLayeredPane layeredPane) {
        layeredPane.repaint();
    }

    public void paintCar(Graphics g) {
        // Create a Graphics2D object for the carLabel's image
        Graphics2D g2d = (Graphics2D) g;

        // Get the current image of the car
        ImageIcon carImageIcon = new ImageIcon("car.png");
        Image carImage = carImageIcon.getImage();

        // Rotate the image based on the rotationAngle
        g2d.rotate(Math.toRadians(rotationAngle), carLabel.getX() + carLabel.getWidth() / 2, carLabel.getY() + carLabel.getHeight() / 2);

        // Draw the car image with the applied rotation
        g2d.drawImage(carImage, carLabel.getX(), carLabel.getY(), null);
    }

    // Reset the car image rotation to the initial state
    public void resetCarRotation() {
        rotationAngle = 0; // Reset the rotation angle back to 0
    }
}

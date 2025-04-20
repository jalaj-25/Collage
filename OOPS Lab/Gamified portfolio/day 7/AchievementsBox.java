import java.awt.*;
import javax.swing.*;

public class AchievementsBox {
    public static void loadContent(JTextField box) {
        box.setLayout(null); // Absolute positioning

        JLabel label = new JLabel("Achivements!");
        label.setFont(new Font("Arial", Font.BOLD, 36));
        label.setForeground(Color.WHITE);
        label.setBounds(750, 30, 600, 50);
        box.add(label);

        // Create 6 independent boxes with adjusted coordinates
        int boxWidth = 300;
        int boxHeight = 300;

        // Adjust the positions of the boxes by rotating 90 degrees
        for (int i = 0; i < 6; i++) {
            JPanel coloredBox = createImagePanel(i); // Set image for each box based on index
            int x = (i % 3) * (boxWidth + 250) + 150;  // Position in columns with 50px gap
            int y = (i / 3) * (boxHeight+ 50) + 100;  // Position in 2 rows with 50px gap
            coloredBox.setBounds(x, y, boxWidth, boxHeight);  // Set position and size of each box
            box.add(coloredBox);
        }
    }

    // Method to create each panel with a different background image
    private static JPanel createImagePanel(int index) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        // Choose background image based on index
        String imagePath = "";
        switch (index) {
            case 0:
                imagePath = "image1.jpg"; // Replace with actual image path
                break;
            case 1:
                imagePath = "image2.jpg"; // Replace with actual image path
                break;
            case 2:
                imagePath = "image3.jpg"; // Replace with actual image path
                break;
            case 3:
                imagePath = "image4.jpg"; // Replace with actual image path
                break;
            case 4:
                imagePath = "image5.jpg"; // Replace with actual image path
                break;
            case 5:
                imagePath = "image6.jpg"; // Replace with actual image path
                break;
        }

        // Set the background image
        try {
            ImageIcon imageIcon = new ImageIcon(imagePath); // Load the image
            Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel background = new JLabel(new ImageIcon(image));
            panel.add(background); // Add the image label to the panel
        } catch (Exception e) {
            System.out.println("Image not found: " + imagePath);
            panel.setBackground(Color.GRAY); // Default color if image fails to load
        }

        return panel;
    }

    // Main method to run and test the RedBox class
    public static void main(String[] args) {
        JFrame frame = new JFrame("Red Box");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField contentBox = new JTextField();
        frame.add(contentBox);
        loadContent(contentBox);

        frame.setVisible(true);
    }
}

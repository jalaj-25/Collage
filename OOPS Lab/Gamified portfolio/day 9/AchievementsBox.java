import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.net.URL;

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
                imagePath = "certificates/achievement1.jpg"; // Remove the leading slash
                break;
            case 1:
                imagePath = "certificates/achievement2.jpg";
                break;
            case 2:
                imagePath = "certificates/achievement3.jpg"; // Fixed duplicate image names
                break;
            case 3:
                imagePath = "certificates/achievement4.jpg"; // Fixed duplicate image names
                break;
            case 4:
                imagePath = "certificates/achievement5.jpg"; // Fixed duplicate image names
                break;
            case 5:
                imagePath = "certificates/achievement6.jpg"; // Fixed duplicate image names
                break;
        }

        // Set the background image using Class.getResource
        try {
            // Try to load image using different methods
            ImageIcon imageIcon = null;
            
            // Method 1: Try loading from class resource
            URL resourceUrl = AchievementsBox.class.getResource("/" + imagePath);
            if (resourceUrl != null) {
                imageIcon = new ImageIcon(resourceUrl);
            }
            
            // Method 2: If Method 1 fails, try loading directly from file system
            if (imageIcon == null || imageIcon.getIconWidth() == -1) {
                File file = new File(imagePath);
                if (file.exists()) {
                    imageIcon = new ImageIcon(file.getAbsolutePath());
                }
            }

            // Check if image was loaded successfully
            if (imageIcon == null || imageIcon.getIconWidth() == -1) {
                System.out.println("Error: Image not found: " + imagePath);
                panel.setBackground(Color.GRAY);
            } else {
                Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                JLabel background = new JLabel(new ImageIcon(image));
                panel.add(background);
            }
        } catch (Exception e) {
            System.out.println("Error loading image: " + imagePath);
            e.printStackTrace();
            panel.setBackground(Color.GRAY);
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

import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.net.URL;

public class ProjectsBox {
    public static void loadContent(JTextField box) {
        box.setLayout(null); // Absolute positioning

        JLabel label = new JLabel("Projects!");
        label.setFont(new Font("Arial", Font.BOLD, 36));
        label.setForeground(Color.WHITE);
        label.setBounds(750, 30, 600, 50);
        box.add(label);

        // Keep original box dimensions
        int boxWidth = 300;   // Original width
        int boxHeight = 300;  // Original height

        for (int i = 0; i < 6; i++) {
            JPanel coloredBox = createImagePanel(i);
            int x = (i % 3) * (boxWidth + 250) + 150;  // Position in columns with 50px gap
            int y = (i / 3) * (boxHeight + 50) + 100;  // Back to original spacing
            coloredBox.setBounds(x, y, boxWidth, boxHeight);
            box.add(coloredBox);
        }
    }

    // Method to create each panel with a different background image
    private static JPanel createImagePanel(int index) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK); // Set background color for empty space
        
        // Add debug information
        File currentDir = new File(".");
        System.out.println("Current working directory: " + currentDir.getAbsolutePath());
        
        // Choose background image based on index
        String imagePath = "";
        switch (index) {
            case 0:
                imagePath = "posters_models/image1.png";
                break;
            case 1:
                imagePath = "posters_models/image2.png";
                break;
            case 2:
                imagePath = "posters_models/image3.png";
                break;
            case 3:
                imagePath = "posters_models/image4.png";
                break;
            case 4:
                imagePath = "posters_models/image5.png";
                break;
            case 5:
                imagePath = "posters_models/image6.png";
                break;
        }

        System.out.println("Looking for image in: " + new File(imagePath).getAbsolutePath());

        try {
            ImageIcon imageIcon = null;
            
            // Method 1: Try loading from class resource
            URL resourceUrl = ProjectsBox.class.getResource("/" + imagePath);
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
                // Get original image dimensions
                int originalWidth = imageIcon.getIconWidth();
                int originalHeight = imageIcon.getIconHeight();
                
                // Keep box size at 300x300
                int maxWidth = 300;
                int maxHeight = 300;
                
                // Calculate scaling factors
                double widthScale = (double) maxWidth / originalWidth;
                double heightScale = (double) maxHeight / originalHeight;
                
                // Use the smaller scaling factor to maintain aspect ratio
                double scale = Math.min(widthScale, heightScale);
                
                // Calculate new dimensions
                int scaledWidth = (int) (originalWidth * scale);
                int scaledHeight = (int) (originalHeight * scale);
                
                // Scale the image
                Image image = imageIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                JLabel background = new JLabel(new ImageIcon(image));
                
                // Center the image in the panel
                background.setHorizontalAlignment(JLabel.CENTER);
                background.setVerticalAlignment(JLabel.CENTER);
                
                panel.add(background, BorderLayout.CENTER);
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

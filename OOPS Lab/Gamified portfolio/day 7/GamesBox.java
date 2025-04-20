import java.awt.*;
import javax.swing.*;

public class GamesBox {

    public static void loadContent(JPanel box) {
        box.setLayout(null); // Absolute positioning

        JLabel label = new JLabel("🎮 Welcome to Games!");
        label.setFont(new Font("Arial", Font.BOLD, 36));
        label.setForeground(Color.WHITE);
        label.setBounds(350, 20, 600, 50);
        box.add(label);

        int boxWidth = 300;
        int boxHeight = 300;

        // Arbitrary positions (not grid-based)
        int[][] positions = {
            {100, 100},
            {500, 80},
            {850, 150},
            {200, 450},
            {600, 400},
            {900, 500}
        };

        for (int i = 0; i < 6; i++) {
            JPanel coloredBox = createImagePanel(i);
            int x = positions[i][0];
            int y = positions[i][1];
            coloredBox.setBounds(x, y, boxWidth, boxHeight);
            box.add(coloredBox);
        }
    }

    private static JPanel createImagePanel(int index) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        String imagePath = "image" + (index + 1) + ".jpg";

        try {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel background = new JLabel(new ImageIcon(image));
            panel.add(background);
        } catch (Exception e) {
            System.out.println("Image not found: " + imagePath);
            panel.setBackground(Color.GRAY);
        }

        return panel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Achievements");
        frame.setSize(1300, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentBox = new JPanel();
        contentBox.setBackground(Color.BLACK);
        contentBox.setLayout(null);
        frame.add(contentBox);

        loadContent(contentBox);

        frame.setVisible(true);
    }
}

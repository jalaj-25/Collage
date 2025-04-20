import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App {
    private static JTextField gamesBox;
    private static JTextField achievementsBox;
    private static JTextField projectsBox;
    private static JTextField loginBox;
    private static JTextField pinkBox;
    private static JLayeredPane layeredPane;

    private static JLabel gamesLabel;
    private static JLabel achievementsLabel;
    private static JLabel projectsLabel;

    private static final int FRAME_WIDTH = 1920;
    private static final int FRAME_HEIGHT = 1080;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Portfolio Demo");
            frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());
            frame.getContentPane().setBackground(Color.BLACK);

            layeredPane = new JLayeredPane();
            layeredPane.setLayout(null);
            layeredPane.setBackground(Color.BLACK);

            JLabel carLabel = new JLabel();
            ImageIcon carIcon = new ImageIcon("car.png");
            if (carIcon.getIconWidth() == -1) {
                System.out.println("Error: Image not found");
            } else {
                Image scaled = carIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                carLabel.setIcon(new ImageIcon(scaled));
            }

            int startX = 800;
            int startY = 400;
            carLabel.setBounds(startX, startY, 150, 150);
            layeredPane.add(carLabel, Integer.valueOf(1));

            int moveAmount = 20;

            InputMap inputMap = layeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = layeredPane.getActionMap();

            inputMap.put(KeyStroke.getKeyStroke("W"), "moveUp");
            inputMap.put(KeyStroke.getKeyStroke("A"), "moveLeft");
            inputMap.put(KeyStroke.getKeyStroke("S"), "moveDown");
            inputMap.put(KeyStroke.getKeyStroke("D"), "moveRight");

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "moveUp");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLeft");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "moveDown");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRight");

            actionMap.put("moveUp", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    carLabel.setLocation(carLabel.getX(), carLabel.getY() - moveAmount);
                    checkHoverAndResizeBoxes(carLabel);
                }
            });
            actionMap.put("moveLeft", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    carLabel.setLocation(carLabel.getX() - moveAmount, carLabel.getY());
                    checkHoverAndResizeBoxes(carLabel);
                }
            });
            actionMap.put("moveDown", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    carLabel.setLocation(carLabel.getX(), carLabel.getY() + moveAmount);
                    checkHoverAndResizeBoxes(carLabel);
                }
            });
            actionMap.put("moveRight", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    carLabel.setLocation(carLabel.getX() + moveAmount, carLabel.getY());
                    checkHoverAndResizeBoxes(carLabel);
                }
            });

            Dimension boxSize = new Dimension(300, 300);

            gamesBox = createBox(Color.RED, 50, 50, boxSize);
            achievementsBox = createBox(Color.GREEN, 700, 50, boxSize);
            projectsBox = createBox(Color.BLUE, 1350, 50, boxSize);
            loginBox = createBox(Color.YELLOW, 1550, 850, new Dimension(100, 50));

            // Labels above boxes
            gamesLabel = createLabel("Games", 150, 20);
            achievementsLabel = createLabel("Achievements", 780, 20);
            projectsLabel = createLabel("Projects", 1450, 20);

            layeredPane.add(gamesLabel, Integer.valueOf(0));
            layeredPane.add(achievementsLabel, Integer.valueOf(0));
            layeredPane.add(projectsLabel, Integer.valueOf(0));

            layeredPane.add(gamesBox, Integer.valueOf(0));
            layeredPane.add(achievementsBox, Integer.valueOf(0));
            layeredPane.add(projectsBox, Integer.valueOf(0));
            layeredPane.add(loginBox, Integer.valueOf(0));

            pinkBox = new JTextField();
            pinkBox.setBackground(Color.PINK);
            pinkBox.setEditable(false);
            pinkBox.setBorder(null);
            pinkBox.setVisible(false);
            layeredPane.add(pinkBox, Integer.valueOf(3));

            layeredPane.setLayer(carLabel, 2);
            frame.add(layeredPane, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }

    private static JTextField createBox(Color color, int x, int y, Dimension size) {
        JTextField box = new JTextField();
        box.setPreferredSize(size);
        box.setBackground(color);
        box.setBorder(null);
        box.setEditable(false);
        box.setFocusable(false);
        box.setText("");
        box.setBounds(x, y, size.width, size.height);
        return box;
    }

    private static JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setBounds(x, y, 300, 30);
        return label;
    }

    private static void checkHoverAndResizeBoxes(JLabel carLabel) {
        Rectangle carBounds = carLabel.getBounds();

        if (pinkBox.isVisible() && pinkBox.getBounds().intersects(carBounds)) {
            resetBoxes();
            return;
        }

        if (gamesBox.getBounds().intersects(carBounds)) {
            expandBox(gamesBox);
        } else if (achievementsBox.getBounds().intersects(carBounds)) {
            expandBox(achievementsBox);
        } else if (projectsBox.getBounds().intersects(carBounds)) {
            expandBox(projectsBox);
        } else if (loginBox.getBounds().intersects(carBounds)) {
            expandBox(projectsBox);
        }
    }

    private static void expandBox(JTextField box) {
        // Expand the box to cover the entire screen
        box.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        box.removeAll();
    
        // Load content for the box
        if (box == gamesBox) {
            GamesBox.loadContent(box);
        } else if (box == achievementsBox) {
            AchievementsBox.loadContent(box);
        } else if (box == projectsBox) {
            ProjectsBox.loadContent(box);
        } else if(box == loginBox) {
            LoginBox.loadContent(box);
        }
    
        // Hide the other boxes and labels
        if (box != gamesBox) {
            gamesBox.setVisible(false);
            gamesLabel.setVisible(false);  // Hide the label for the other boxes
        }
        if (box != achievementsBox) {
            achievementsBox.setVisible(false);
            achievementsLabel.setVisible(false);  // Hide the label for the other boxes
        }
        if (box != projectsBox) {
            projectsBox.setVisible(false);
            projectsLabel.setVisible(false);  // Hide the label for the other boxes
        }
        if (box != loginBox) {
            loginBox.setVisible(false);
        }
    
        // Show pinkBox
        pinkBox.setBounds(800, 800, 120, 50);
        pinkBox.setVisible(true);
    
        // Revalidate and repaint to apply changes
        box.revalidate();
        box.repaint();
    }   
    
    private static void resetBoxes() {
        // Reset positions and sizes
        gamesBox.setBounds(50, 50, 300, 300);
        achievementsBox.setBounds(700, 50, 300, 300);
        projectsBox.setBounds(1350, 50, 300, 300);
        loginBox.setBounds(1350, 50, 300, 300);
    
        gamesBox.removeAll();
        achievementsBox.removeAll();
        projectsBox.removeAll();
        loginBox.removeAll();
    
        // Make all boxes and their labels visible again
        gamesBox.setVisible(true);
        achievementsBox.setVisible(true);
        projectsBox.setVisible(true);
        loginBox.setVisible(true);
    
        gamesLabel.setVisible(true);
        achievementsLabel.setVisible(true);
        projectsLabel.setVisible(true);
    
        pinkBox.setVisible(false);
    
        gamesBox.repaint();
        achievementsBox.repaint();
        projectsBox.repaint();
        loginBox.repaint();
    }
    
}

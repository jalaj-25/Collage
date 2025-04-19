import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App {
    private static JTextField topBox1;
    private static JTextField topBox2;
    private static JTextField topBox3;
    private static JButton backButton;

    private static final int FRAME_WIDTH = 1920;
    private static final int FRAME_HEIGHT = 1080;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Car Movement Demo");
            frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());
            frame.getContentPane().setBackground(Color.BLACK);

            JLayeredPane layeredPane = new JLayeredPane();
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

            topBox1 = createBox(Color.RED, 50, 50, boxSize);
            topBox2 = createBox(Color.GREEN, 700, 50, boxSize);
            topBox3 = createBox(Color.BLUE, 1350, 50, boxSize);

            layeredPane.add(topBox1, Integer.valueOf(0));
            layeredPane.add(topBox2, Integer.valueOf(0));
            layeredPane.add(topBox3, Integer.valueOf(0));

            // === Back Button ===
            backButton = new JButton("Back");
            int buttonWidth = 120;
            int buttonHeight = 50;
            int centerX = (FRAME_WIDTH - buttonWidth) / 2;
            int bottomY = FRAME_HEIGHT - buttonHeight - 50;
            backButton.setBounds(centerX, bottomY, buttonWidth, buttonHeight);
            backButton.setVisible(false);
            backButton.setFocusable(false);

            backButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    resetBoxes();
                    backButton.setVisible(false);
                }
            });

            layeredPane.add(backButton, Integer.valueOf(3));

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

    private static void checkHoverAndResizeBoxes(JLabel carLabel) {
        Rectangle carBounds = carLabel.getBounds();

        if (topBox1.getBounds().intersects(carBounds)) {
            expandBox(topBox1);
        } else if (topBox2.getBounds().intersects(carBounds)) {
            expandBox(topBox2);
        } else if (topBox3.getBounds().intersects(carBounds)) {
            expandBox(topBox3);
        }
    }

    private static void expandBox(JTextField box) {
        box.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        backButton.setVisible(true);
    }

    private static void resetBoxes() {
        topBox1.setBounds(50, 50, 300, 300);
        topBox2.setBounds(700, 50, 300, 300);
        topBox3.setBounds(1350, 50, 300, 300);
    }
}

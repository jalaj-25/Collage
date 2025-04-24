import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.net.URL;

public class AcademicsBox {
    public static void loadContent(JTextField box) {
        box.setLayout(null); // Absolute positioning

        JLabel label = new JLabel("Academics!");
        label.setFont(new Font("Arial", Font.BOLD, 36));
        label.setForeground(Color.WHITE);
        label.setBounds(750, 30, 600, 50);
        box.add(label);

        // Create a panel for academic information
        JPanel academicPanel = createAcademicPanel();
        academicPanel.setBounds(150, 100, 1500, 800);  // Adjust size as needed
        box.add(academicPanel);
    }

    private static JPanel createAcademicPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0, 0, 0, 80)); // Semi-transparent black

        // Add academic details with styling
        addAcademicSection(panel, "Education", 50, 50);
        addAcademicSection(panel, "Grades", 50, 300);
        addAcademicSection(panel, "Achievements", 800, 50);
        addAcademicSection(panel, "Skills", 800, 300);

        return panel;
    }

    private static void addAcademicSection(JPanel panel, String title, int x, int y) {
        // Section Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(x, y, 300, 40);
        panel.add(titleLabel);

        // Section Content
        JTextArea contentArea = new JTextArea();
        contentArea.setFont(new Font("Arial", Font.PLAIN, 16));
        contentArea.setForeground(Color.WHITE);
        contentArea.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setBounds(x, y + 45, 600, 200);

        // Add content based on section
        switch (title) {
            case "Education":
                contentArea.setText(
                    "• University of Petroleum and Energy Studies (2022-2026)\n" +
                    "  B.Tech in Computer Science Engineering\n\n" +
                    "• Your School Name (Year-Year)\n" +
                    "  Class XII - XX%\n\n" +
                    "• Your School Name (Year-Year)\n" +
                    "  Class X - XX%"
                );
                break;
            case "Grades":
                contentArea.setText(
                    "CGPA by Semester:\n\n" +
                    "• Semester 1: X.XX\n" +
                    "• Semester 2: X.XX\n" +
                    "• Semester 3: X.XX\n" +
                    "• Current CGPA: X.XX"
                );
                break;
            case "Achievements":
                contentArea.setText(
                    "• Dean's List - All Semesters\n" +
                    "• First Prize in College Hackathon\n" +
                    "• Department Rank Holder\n" +
                    "• Technical Club Lead\n" +
                    "• Certification in [Your Certification]"
                );
                break;
            case "Skills":
                contentArea.setText(
                    "Technical Skills:\n" +
                    "• Programming: Java, Python, C++\n" +
                    "• Web: HTML, CSS, JavaScript\n" +
                    "• Tools: Git, VS Code, Eclipse\n\n" +
                    "Soft Skills:\n" +
                    "• Team Leadership\n" +
                    "• Problem Solving\n" +
                    "• Communication"
                );
                break;
        }

        panel.add(contentArea);
    }

    // Optional: Add method to create a styled border
    private static JPanel createStyledPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBackground(new Color(0, 0, 0, 80));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        return panel;
    }
}

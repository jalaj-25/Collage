import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage {
    // Hardcoded username and password for simplicity
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080); // Set the size of the window to 1920x1080
        frame.setLayout(new BorderLayout());

        // Center Panel for the form (using GridBagLayout for better control)
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.CYAN); // Set the background color to light blue (Cyan)

        // GridBagConstraints for controlling placement and sizing
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Adding some padding around components
        gbc.anchor = GridBagConstraints.CENTER; // Centering components

        // Username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);  // Set width of the field

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20); // Set width of the field

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));

        // Add components to the panel using GridBagConstraints
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2; // Make the username label span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make the button expand to fill horizontal space
        panel.add(loginButton, gbc);

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                String enteredPassword = new String(passwordField.getPassword());

                // Check credentials
                if (enteredUsername.equals(USERNAME) && enteredPassword.equals(PASSWORD)) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Show the frame centered on the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

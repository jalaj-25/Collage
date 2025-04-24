import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

public class RegisterPage {
    // A simple in-memory collection to store registered usernames (for demonstration purposes)
    private static final HashSet<String> registeredUsernames = new HashSet<>();

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Register Page");
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

        // Confirm Password label and password field
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordField = new JPasswordField(20); // Set width of the field

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.PLAIN, 18));

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
        panel.add(confirmPasswordLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(confirmPasswordField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make the button expand to fill horizontal space
        panel.add(registerButton, gbc);

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Action listener for register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                String enteredPassword = new String(passwordField.getPassword());
                String enteredConfirmPassword = new String(confirmPasswordField.getPassword());

                // Check if passwords match
                if (!enteredPassword.equals(enteredConfirmPassword)) {
                    JOptionPane.showMessageDialog(frame, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if the username is already taken
                if (registeredUsernames.contains(enteredUsername)) {
                    JOptionPane.showMessageDialog(frame, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Register the user
                registeredUsernames.add(enteredUsername);
                JOptionPane.showMessageDialog(frame, "Registration Successful!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Show the frame centered on the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginBox {

    public static void loadContent(JTextField loginBox) {
        // Set the layout for the login box to a simple grid layout
        loginBox.setLayout(new BorderLayout());

        // Create a label for the username input field
        JLabel usernameLabel = new JLabel("Enter Username:");
        usernameLabel.setForeground(Color.BLACK);  // Black text for the label
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Create the text field for entering the username
        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));

        // Add the username label and text field to the login box
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(usernameLabel);
        panel.add(usernameField);

        // Create a login button to simulate the login action
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));
        loginButton.setBackground(Color.GREEN);
        loginButton.setForeground(Color.WHITE);

        // Add an action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a username.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Welcome, " + username + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Add the panel and the login button to the loginBox
        loginBox.add(panel, BorderLayout.CENTER);
        loginBox.add(loginButton, BorderLayout.SOUTH);

        // Revalidate and repaint to ensure the layout is updated
        loginBox.revalidate();
        loginBox.repaint();
    }
}

package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChangePasswordPage extends JFrame {

    private JPanel changePanel, buttonPanel, textPanel;
    private JButton changeButton;
    private JLabel new_passwordLabel, confirm_new_passwordLabel, textLabel;
    private JPasswordField new_passwordField, confirm_new_passwordField;

    ChangePasswordPage(){
        setTitle("Change Password!");
        setSize(400, 200);
        setLocationRelativeTo(null);

        EmptyBorder border = new EmptyBorder(20,10, 20, 20);

        new_passwordLabel = new JLabel("new password: ");
        new_passwordLabel.setBorder(border);
        confirm_new_passwordLabel = new JLabel("confirm new password: ");
        confirm_new_passwordLabel.setBorder(border);

        Border border2 = BorderFactory.createLineBorder(Color.GRAY, 2);

        new_passwordField = new JPasswordField();
        new_passwordField.setBorder(border2);
        confirm_new_passwordField = new JPasswordField();
        confirm_new_passwordField.setBorder(border2);

        changeButton = new JButton("Change password!");
        changeButton.setBorder(border);
        changeButton.setBackground(Color.GREEN);

        changePanel = new JPanel();
        LayoutManager layout = new GridLayout(2, 2, 15, 15);
        changePanel.setLayout(layout);

        changePanel.add(new_passwordLabel);
        changePanel.add(new_passwordField);
        changePanel.add(confirm_new_passwordLabel);
        changePanel.add(confirm_new_passwordField);

        add(changePanel);

        buttonPanel = new JPanel();
        buttonPanel.add(changeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        textPanel = new JPanel();
        textLabel = new JLabel("Please, change your password: ");
        textPanel.add(textLabel);

        add(textPanel, BorderLayout.NORTH);

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


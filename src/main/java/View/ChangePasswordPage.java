package View;

import Controller.AccountController;
import lombok.Getter;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Getter

public class ChangePasswordPage extends JFrame{

    private JPanel changePanel, buttonPanel, textPanel;
    private JButton changeButton;
    private JLabel newPasswordLabel, confirmNewPasswordLabel, textLabel, currentUserLabel;
    private JPasswordField newPasswordField, confirmNewPasswordField;
    private JTextField currentUserField;

    ChangePasswordPage() {
        setTitle("Change Password!");
        setSize(400, 300);
        setLocationRelativeTo(null);

        EmptyBorder border = new EmptyBorder(20, 10, 20, 20);

        currentUserLabel = new JLabel("Currrnt username: ");
        currentUserLabel.setBorder(border);

        newPasswordLabel = new JLabel("new password: ");
        newPasswordLabel.setBorder(border);
        confirmNewPasswordLabel = new JLabel("confirm new password: ");
        confirmNewPasswordLabel.setBorder(border);

        Border border2 = BorderFactory.createLineBorder(Color.GRAY, 2);

        currentUserField = new JTextField();
        currentUserField.setBorder(border2);

        newPasswordField = new JPasswordField();
        newPasswordField.setBorder(border2);
        confirmNewPasswordField = new JPasswordField();
        confirmNewPasswordField.setBorder(border2);

        changeButton = new JButton("Change password!");
        changeButton.setBorder(border);
        changeButton.setBackground(Color.GREEN);

        changePanel = new JPanel();
        LayoutManager layout = new GridLayout(3, 3, 15, 15);
        changePanel.setLayout(layout);

        changePanel.add(currentUserLabel);
        changePanel.add(currentUserField);

        changePanel.add(newPasswordLabel);
        changePanel.add(newPasswordField);
        changePanel.add(confirmNewPasswordLabel);
        changePanel.add(confirmNewPasswordField);

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

        changeButton.addActionListener(e -> {
            AccountController accountController = new AccountController(this);
            if (true == accountController.validateChangePassword()) {
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
                dispose();
            }
        });
    }

    public static void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public static void showMessageSuccess(String msg) {
        JOptionPane.showMessageDialog(null, msg, "SUCCES", JOptionPane.INFORMATION_MESSAGE);
    }
}


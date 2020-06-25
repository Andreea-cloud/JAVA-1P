package View;

import Controller.HistoryController;
import Controller.LoginController;
import Controller.Session;
import lombok.Getter;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
@Getter
public class LoginPage extends JFrame {

    private JPanel panel;
    private JButton loginButton, cancelButton;
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    LoginPage(){
        setTitle("Login Page");
        setSize(450, 180);
        setLocationRelativeTo(null);

        EmptyBorder border = new EmptyBorder(50,30, 50, 30);

        usernameLabel = new JLabel("UserName / E-mail: ");
        usernameLabel.setFont(new Font("Time New Roman", Font.BOLD, 15));
        usernameLabel.setBorder(border);
        passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Time New Roman", Font.BOLD, 15));
        passwordLabel.setBorder(border);

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        Border border2 = BorderFactory.createLineBorder(Color.GRAY, 2);
        passwordField.setBorder(border2);
        usernameField.setBorder(border2);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Time New Roman", Font.ITALIC, 20));
        loginButton.setBorder(border);
        loginButton.setBackground(Color.PINK);
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Time New Roman", Font.ITALIC, 20));
        cancelButton.setBorder(border);
        cancelButton.setBackground(Color.PINK);

        panel = new JPanel(new GridLayout(3, 2,15, 15));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(cancelButton);

        panel.setBackground(Color.RED);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(panel, BorderLayout.CENTER);
        setVisible(true);

        loginButton.addActionListener(e -> {
            LoginController loginController = new LoginController(this);
            if(true == loginController.validateLoginUser()) {
                HistoryController.logAction("Login");
                MainPage main = new MainPage();
                main.setVisible(true);
                dispose();
            }
        });
        cancelButton.addActionListener(e -> {
            StartPage start = new StartPage();
            start.setVisible(true);
            dispose();
        });
    }

    public static void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
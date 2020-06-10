package View;

import Controller.LoginController;
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
        setSize(400, 150);
        setLocationRelativeTo(null);

        EmptyBorder border = new EmptyBorder(50,30, 50, 30);

        usernameLabel = new JLabel("UserName / E-mail: ");
        usernameLabel.setBorder(border);
        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBorder(border);

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        Border border2 = BorderFactory.createLineBorder(Color.GRAY, 2);
        passwordField.setBorder(border2);
        usernameField.setBorder(border2);

        loginButton = new JButton("Login");
        loginButton.setBorder(border);
        loginButton.setBackground(Color.GREEN);
        cancelButton = new JButton("Cancel");
        cancelButton.setBorder(border);
        cancelButton.setBackground(Color.GREEN);

        panel = new JPanel(new GridLayout(3, 2,15, 15));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(cancelButton);

        panel.setBackground(Color.GRAY);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(panel, BorderLayout.CENTER);
        setVisible(true);

        loginButton.addActionListener(e -> {
            LoginController loginController = new LoginController(this);
            if(true == loginController.validateLoginUser()) {
//              MainPage main = new MainPage(getUsernameField().toString(), getUsernameField().toString());
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

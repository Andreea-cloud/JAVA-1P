package View;

import Controller.RegisterController;
import lombok.Getter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
@Getter

public class RegisterPage extends JFrame {

    private JPanel panel;
    private JButton registerButton, cancelButton;
    private JLabel usernameLabel, passwordLabel, passwordConfirmationLabel, emailLabel;

    private JTextField usernameField, emailField;
    private JPasswordField passwordField, passwordConfirmationField;

    RegisterPage(){

        setTitle("Register Page");
        setSize(450, 200);
        setLocationRelativeTo(null);
        setVisible(true);

        EmptyBorder border = new EmptyBorder(50,30, 50, 30);

        usernameLabel = new JLabel("UserName: ");
        usernameLabel.setBorder(border);
        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBorder(border);
        passwordConfirmationLabel = new JLabel("Password confirmation: ");
        passwordConfirmationLabel.setBorder(border);
        emailLabel = new JLabel("E-mail adress: ");
        emailLabel.setBorder(border);

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        passwordConfirmationField = new JPasswordField();
        emailField = new JTextField();

        registerButton = new JButton("Register");
        cancelButton = new JButton("Cancel");

        panel = new JPanel(new GridLayout(5, 2, 15, 15));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(passwordConfirmationLabel);
        panel.add(passwordConfirmationField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(registerButton);
        panel.add(cancelButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(panel, BorderLayout.CENTER);
        panel.setBackground(Color.ORANGE);

        registerButton.addActionListener(e -> {
            RegisterController registerController = new RegisterController(this);
            if(true == registerController.validateAndRegisterUser()) {
                LoginPage login = new LoginPage();
                login.setVisible(true);
                dispose();

            }
            else {
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


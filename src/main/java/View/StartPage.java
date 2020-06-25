package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StartPage extends JFrame {

    private JPanel colorPanel;
    private JPanel buttonPanel;
    private JLabel label;
    private JButton registerButton, loginButton;

    StartPage() {
        setTitle("Pagina de Start");
        setSize(350, 200);
        setLocationRelativeTo(null);

        colorPanel = new JPanel();
        add(colorPanel);
        colorPanel.setBackground(Color.CYAN);

        EmptyBorder border2 = new EmptyBorder(40,30, 40, 30);

        label = new JLabel("Bine ati venit - aplicatia dumneavoastra de zboruri!");
        colorPanel.add(label);
        label.setBorder(border2);

        buttonPanel = new JPanel();
        LayoutManager layout = new FlowLayout();
        buttonPanel.setLayout(layout);
        buttonPanel.setBackground(Color.gray);

        EmptyBorder border = new EmptyBorder(10, 10, 10, 10);

        registerButton = new JButton("Register");
        registerButton.setBorder(border);
        registerButton.setBackground(Color.CYAN);
        buttonPanel.add(registerButton);

        loginButton = new JButton("Login");
        loginButton.setBorder(border);
        loginButton.setBackground(Color.CYAN);
        buttonPanel.add(loginButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        loginButton.addActionListener(e -> {
            LoginPage login = new LoginPage();
            login.setVisible(true);
            dispose();
        });

        registerButton.addActionListener(e -> {
            RegisterPage registerPage = new RegisterPage();
            registerPage.setVisible(true);
            dispose();
        });
    }
}
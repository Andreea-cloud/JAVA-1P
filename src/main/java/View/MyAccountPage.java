package View;

import Controller.AccountController;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Getter

public class MyAccountPage extends MenuBarBase {

    private JPanel buttonPanel, textPanel, changePanel;
    private JButton changeEmailButton, changeUsernameButton, changePasswordButton, historyButton;
    private JLabel new_emailLabel, usernameLabel, confirmuserLabel, new_usernameLabel, textLabel;

//    FIXME change naming to camelCase format
    private JTextField new_emailField, usernameField, new_usernameField;

    MyAccountPage(){
        super();
        setTitle("My Account!!!");
        setSize(500, 300);
        setLocationRelativeTo(null);

        textPanel = new JPanel();
        textPanel.setBackground(Color.gray);
        textLabel = new JLabel("Change Username & Email: ");
        textPanel.add(textLabel, BorderLayout.CENTER);
        add(textPanel,BorderLayout.NORTH);

        changePanel = new JPanel();
        changePanel.setBackground(Color.gray);
        EmptyBorder border = new EmptyBorder(20,10, 20, 10);
        LayoutManager layout = new GridLayout(4, 3, 15, 15);
        changePanel.setLayout(layout);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.gray);

        new_emailLabel = new JLabel("new_email: ");
        new_emailLabel.setBorder(border);
        usernameLabel = new JLabel("Current user!");
        usernameLabel.setBorder(border);
        confirmuserLabel = new JLabel("");
        confirmuserLabel.setBorder(border);
        new_usernameLabel = new JLabel("new_username: ");
        new_usernameLabel.setBorder(border);

        new_emailField = new JTextField();
        usernameField = new JTextField();
        new_usernameField = new JTextField();

        changeEmailButton = new JButton("Change Email: ");
        changeEmailButton.setBorder(border);
        changeEmailButton.setBackground(Color.yellow);
        changeUsernameButton = new JButton("Change Username: ");
        changeUsernameButton.setBorder(border);
        changeUsernameButton.setBackground(Color.yellow);
        historyButton = new JButton("View history - AUDIT - : ");
        historyButton.setBorder(border);
        historyButton.setBackground(Color.CYAN);
        changePasswordButton = new JButton("Change Password: ");
        changePasswordButton.setBorder(border);
        changePasswordButton.setBackground(Color.CYAN);

        changePanel.add(usernameLabel);
        changePanel.add(usernameField);
        changePanel.add(confirmuserLabel);
        changePanel.add(new_emailLabel);
        changePanel.add(new_emailField);
        changePanel.add(changeEmailButton);
        changePanel.add(new_usernameLabel);
        changePanel.add(new_usernameField);
        changePanel.add(changeUsernameButton);

        add(changePanel);

        buttonPanel.add(changePasswordButton);
        buttonPanel.add(historyButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        changeUsernameButton.addActionListener( e -> {
            AccountController accountController = new AccountController(this);
            if(true == accountController.validateChangeUser()){
                MyAccountPage myAccountPage = new MyAccountPage();
                myAccountPage.setVisible(true);
                dispose();
            }else{
            }
        });

        changeEmailButton.addActionListener(e -> {
            AccountController accountController = new AccountController(this);
            if(true == accountController.validateChangeEmail()){
                MyAccountPage myAccountPage = new MyAccountPage();
                myAccountPage.setVisible(true);
                dispose();
            }else{
            }
        });

        changePasswordButton.addActionListener(e -> {
            ChangePasswordPage changePassword = new ChangePasswordPage();
            changePassword.setVisible(true);
            dispose();
        });

        historyButton.addActionListener(e -> {
            HistoryPage history = new HistoryPage();
            history.setVisible(true);
            dispose();
        });
    }
    public static void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}


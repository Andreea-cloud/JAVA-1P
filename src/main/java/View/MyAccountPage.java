package View;

import Controller.AccountController;
import Controller.Session;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Getter

public class MyAccountPage extends MenuBarBase {

    private JPanel buttonPanel, textPanel, changePanel;
    private JButton changeEmailButton, changeUsernameButton, changePasswordButton, historyButton;
    private JLabel newEmailLabel, usernameLabel, newUsernameLabel, textLabel;

//    FIXME change naming to camelCase format
    private JTextField newEmailField, usernameField, newUsernameField,  emailField;

    MyAccountPage(){
        super();
        setTitle("My Account!!!");
        setSize(550, 300);
        setLocationRelativeTo(null);

        textPanel = new JPanel();
        textPanel.setBackground(Color.RED);
        textLabel = new JLabel("Change Username & Email: ");
        textLabel.setFont(new Font("Time New Roman", Font.ITALIC, 18));
        textPanel.add(textLabel, BorderLayout.CENTER);
        add(textPanel,BorderLayout.NORTH);

        changePanel = new JPanel();
        changePanel.setBackground(Color.RED);
        EmptyBorder border = new EmptyBorder(20,10, 20, 10);
        LayoutManager layout = new GridLayout(4, 3, 15, 15);
        changePanel.setLayout(layout);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.RED);

        newEmailLabel = new JLabel("New_email: ");
        newEmailLabel.setFont(new Font("Time New Roman", Font.BOLD, 15));
        newEmailLabel.setBorder(border);
        usernameLabel = new JLabel("Current user!");
        usernameLabel.setFont(new Font("Time New Roman", Font.BOLD, 15));
        usernameLabel.setBorder(border);
        newUsernameLabel = new JLabel("New_username: ");
        newUsernameLabel.setFont(new Font("Time New Roman", Font.BOLD, 15));
        newUsernameLabel.setBorder(border);

        emailField = new JTextField();
        emailField.setFont(new Font("Time New Roman", Font.BOLD, 15));
        emailField.setText(Session.getInstance().getAuthEmail());
        emailField.setEditable(false);
        newEmailField = new JTextField();
        usernameField = new JTextField();
        usernameField.setFont(new Font("Time New Roman", Font.BOLD, 15));
        usernameField.setText(Session.getInstance().getAuthUserName());
        usernameField.setEditable(false);
        newUsernameField = new JTextField();

        changeEmailButton = new JButton("Change Email!!! ");
        changeEmailButton.setFont(new Font("Time New Roman", Font.ITALIC, 15));
        changeEmailButton.setBorder(border);
        changeEmailButton.setBackground(Color.LIGHT_GRAY);
        changeUsernameButton = new JButton("Change Username!!! ");
        changeUsernameButton.setFont(new Font("Time New Roman", Font.ITALIC, 15));
        changeUsernameButton.setBorder(border);
        changeUsernameButton.setBackground(Color.LIGHT_GRAY);
        historyButton = new JButton("View history - AUDIT - ! ");
        historyButton.setFont(new Font("Time New Roman", Font.ITALIC, 15));
        historyButton.setBorder(border);
        historyButton.setBackground(Color.PINK);
        changePasswordButton = new JButton("Change Password! ");
        changePasswordButton.setFont(new Font("Time New Roman", Font.ITALIC, 15));
        changePasswordButton.setBorder(border);
        changePasswordButton.setBackground(Color.PINK);

        changePanel.add(usernameLabel);
        changePanel.add(usernameField);
        changePanel.add(emailField);
        changePanel.add(newEmailLabel);
        changePanel.add(newEmailField);
        changePanel.add(changeEmailButton);
        changePanel.add(newUsernameLabel);
        changePanel.add(newUsernameField);
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

    public static void showMessageSuccess(String msg) {
        JOptionPane.showMessageDialog(null, msg, "SUCCES", JOptionPane.INFORMATION_MESSAGE);
    }
}


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

        newEmailLabel = new JLabel("new_email: ");
        newEmailLabel.setBorder(border);
        usernameLabel = new JLabel("Current user!");
        usernameLabel.setBorder(border);
        newUsernameLabel = new JLabel("new_username: ");
        newUsernameLabel.setBorder(border);

        emailField = new JTextField();
        emailField.setText(Session.getInstance().getAuthEmail());
        emailField.setEditable(false);
        newEmailField = new JTextField();
        usernameField = new JTextField();
        usernameField.setText(Session.getInstance().getAuthUserName());
        usernameField.setEditable(false);
        newUsernameField = new JTextField();

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


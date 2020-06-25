package View;

import Controller.HistoryController;
import javax.swing.*;
import java.util.Stack;

public class MenuBarBase extends JFrame {

    public JMenuBar menuBar;
    public JMenuItem homeItem, myAccountItem, logoutItem, historyItem, backItem;
    protected String authEmail;
    protected String authUsername;

    static Stack<JPanel> backPanels;

    protected void setAuthUsername(String username) {
        this.authUsername = username;
    }

    protected void setAuthEmail(String email) {
        this.authEmail = email;
    }

    MenuBarBase() {
        initMenu();
    }

    MenuBarBase(String username, String email) {
        authEmail    = email;
        authUsername = username;
        initMenu();
    }

        public void initMenu(){
            menuBar       = new JMenuBar();
            homeItem      = new JMenuItem("Home");
            myAccountItem = new JMenuItem("My Account");
            logoutItem    = new JMenuItem("Log out");
            historyItem   = new JMenuItem("History");
            backItem      = new JMenuItem("Back");

            backPanels = new Stack<JPanel>();

            homeItem.addActionListener(event ->{
                MainPage mainPage = new MainPage();
                HistoryController.logAction("Navigate to main page!");
                mainPage.setVisible(true);
                dispose();
            });
            myAccountItem.addActionListener(event ->{
                MyAccountPage myAccountPage = new MyAccountPage();
                HistoryController.logAction("Navigate to my account page!");
                myAccountPage.setVisible(true);
                dispose();
            });
            logoutItem.addActionListener(event -> {
                LoginPage loginPage = new LoginPage();
                HistoryController.logAction("Logout!");
                loginPage.setVisible(true);
                dispose();
            });
            historyItem.addActionListener(event -> {
                HistoryPage historyPage = new HistoryPage();
                HistoryController.logAction("Navigate to history page!");
                historyPage.setVisible(true);
                dispose();
            });

            menuBar.add(homeItem);
            menuBar.add(myAccountItem);
            menuBar.add(historyItem);
            menuBar.add(logoutItem);
            menuBar.add(backItem);

            setJMenuBar(menuBar);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
}
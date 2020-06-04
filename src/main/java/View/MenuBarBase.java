package View;

import javax.swing.*;

public class MenuBarBase extends JFrame {

    public JMenuBar menuBar;
    public JMenuItem homeItem, myAccountItem, logoutItem, historyItem, backItem;
    protected String authEmail;
    protected String authUsername;

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
        authEmail = email;
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

            homeItem.addActionListener(event ->{
                MainPage mainPage = new MainPage();
                mainPage.setVisible(true);
                dispose();
            });
            myAccountItem.addActionListener(event ->{
                MyAccountPage myAccountPage = new MyAccountPage();
                myAccountPage.setVisible(true);
                dispose();
            });
            logoutItem.addActionListener(event -> {
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
                dispose();
            });

            historyItem.addActionListener(event -> {
                HistoryPage historyPage = new HistoryPage();
                historyPage.setVisible(true);
                dispose();
            });

            backItem.addActionListener(event -> {
            });

            menuBar.add(homeItem);
            menuBar.add(myAccountItem);
            menuBar.add(logoutItem);
            menuBar.add(historyItem);
            menuBar.add(backItem);

            setJMenuBar(menuBar);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
}

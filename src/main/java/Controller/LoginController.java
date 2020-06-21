package Controller;

import Dao.HistoryDao;
import Dao.UsersDao;
import Database.DatabaseConnection;
import Model.Users;
import View.LoginPage;
import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

public class LoginController {
    private UsersDao usersDao;
    private LoginPage viewLoginPage;

    public LoginController (LoginPage view){
//        FIXME send usersDao as parameter
        usersDao = UsersDao.getInstance();
        viewLoginPage = view;
    }

    public boolean validateLoginUser(){

        String usernameOrEmail      = viewLoginPage.getUsernameField().getText();
        char[] input                = viewLoginPage.getPasswordField().getPassword();
        String passwd               = new String(input);

        List<Users> users = usersDao.findAll();

        for (Users temp : users) {
            // matches username
            if (temp.getUsername().equals(usernameOrEmail)) {
                if (temp.getPassword().equals(passwd)) {
                    Session.getInstance().setAuthUserName(usernameOrEmail);
                    Session.getInstance().setAuthEmail(temp.getEmail());
                    return true;
                }
            // matches email
            } else if (temp.getEmail().equals(usernameOrEmail)) {
                if (temp.getPassword().equals(passwd)) {
                    Session.getInstance().setAuthEmail(usernameOrEmail);
                    Session.getInstance().setAuthUserName(temp.getUsername());
                    return true;
                }
            }
        }

        viewLoginPage.showMessage("Username or password is wrong! Please try again!");
        viewLoginPage.getUsernameField().setText("");
        viewLoginPage.getPasswordField().setText("");
        viewLoginPage.getUsernameField().requestFocus();
        return false;
    }
}

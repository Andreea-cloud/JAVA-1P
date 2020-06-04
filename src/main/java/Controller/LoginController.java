package Controller;

import Dao.UsersDao;
import Database.DatabaseConnection;
import Model.Users;
import View.LoginPage;
import java.sql.Connection;
import java.util.List;

public class LoginController {
    private UsersDao usersDao;
    private LoginPage viewLoginPage;

    public LoginController (LoginPage view){
        Connection connection = DatabaseConnection.getConnection();
//        FIXME send usersDao as parameter
        usersDao = new UsersDao(connection);
        viewLoginPage = view;
    }

    public boolean validateLoginUser(){

        String username      = viewLoginPage.getUsernameField().getText();
        char[] input         = viewLoginPage.getPasswordField().getPassword();
        String passwd        = new String(input);

        List<Users> users = usersDao.findAll();

        for (Users temp : users) {
            if(temp.getUsername().equals(username) || temp.getEmail().equals(username)){
               if(temp.getPassword().equals(passwd))
                   return true;
            }
        }

        viewLoginPage.showMessage("Username or password is wrong! Please try again!");
        viewLoginPage.getUsernameField().setText("");
        viewLoginPage.getPasswordField().setText("");
        viewLoginPage.getUsernameField().requestFocus();
        return false;
    }
}

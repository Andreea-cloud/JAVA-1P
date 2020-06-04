package Controller;

import Dao.UsersDao;
import Database.DatabaseConnection;
import Model.Users;
import View.ChangePasswordPage;
import View.MyAccountPage;
import java.sql.Connection;
import java.util.List;

public class AccountController {
    private UsersDao usersDao;
    private MyAccountPage viewMyAccountPage;
    private ChangePasswordPage viewChangePasswordPage;

    private static final String EMAIL_REGEX      = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    public AccountController(MyAccountPage view) {
        Connection connection = DatabaseConnection.getConnection();
        usersDao = new UsersDao(connection);
        viewMyAccountPage = view;
    }

    public boolean validateChangeUser() {
        String currentUsername = viewMyAccountPage.getUsernameField().getText();
        String newUsername = viewMyAccountPage.getNew_usernameField().getText();

        List<Users> users = usersDao.findAll();

        if (!currentUsername.isEmpty()) {
            for (Users temp : users) {
                if (newUsername.isEmpty() || temp.getUsername().equals(newUsername)){
                    viewMyAccountPage.showMessage("Username already taken, please select another one!");
                    viewMyAccountPage.getNew_usernameField().setText("");
                    viewMyAccountPage.getNew_usernameField().requestFocus();
                    return false;
                }
            }
        } else {
            viewMyAccountPage.showMessage("Current username cannot be empty!");
            viewMyAccountPage.getUsernameField().requestFocus();
            return false;
        }
        usersDao.updateUsername(newUsername, currentUsername );
        // TODO check the result of update query
        return true;
    }

    public boolean validateChangeEmail() {
        String currentUsername = viewMyAccountPage.getUsernameField().getText();
        String newEmail = viewMyAccountPage.getNew_emailField().getText();

        List<Users> users = usersDao.findAll();

        if (!currentUsername.isEmpty()) {
            for (Users temp : users) {
                if (!newEmail.isEmpty()) {
                    if (!newEmail.matches(EMAIL_REGEX)) {
                        viewMyAccountPage.showMessage("Email format is not valid!");
                        viewMyAccountPage.getNew_emailField().setText("");
                        viewMyAccountPage.getNew_emailField().requestFocus();
                        return false;
                    }
                }else{
                    viewMyAccountPage.showMessage("Email connot be empty!");
                }
            }
        } else {
            viewMyAccountPage.showMessage("Current username cannot be empty!");
            viewMyAccountPage.getUsernameField().requestFocus();
            return false;
        }
        usersDao.updateEmail(newEmail, currentUsername );
        // TODO check the result of update query
        return true;
    }
}

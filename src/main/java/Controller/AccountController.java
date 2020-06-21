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
    private static final String UPPER_CASE_REGEX ="(.*[A-Z].*)";
    private static final String LOWER_CASE_REGEX ="(.*[a-z].*)";
    private static final String NUMBERS_REGEX    = "(.*[0-9].*)";

    public AccountController(MyAccountPage view) {
        Connection connection = DatabaseConnection.getConnection();
        usersDao = new UsersDao(connection);
        viewMyAccountPage = view;
    }

    public AccountController(ChangePasswordPage view){
        Connection connection = DatabaseConnection.getConnection();
        usersDao = new UsersDao(connection);
        viewChangePasswordPage = view;
    }

    public boolean validateChangeUser() {
        String currentUsername = viewMyAccountPage.getUsernameField().getText();
        String newUsername = viewMyAccountPage.getNewUsernameField().getText();
        List<Users> users = usersDao.findAll();

        if (!currentUsername.isEmpty()) {
            for (Users temp : users) {
                if (newUsername.isEmpty() || temp.getUsername().equals(newUsername)){
                    viewMyAccountPage.showMessage("Username already taken, please select another one!");
                    viewMyAccountPage.getNewUsernameField().setText("");
                    viewMyAccountPage.getNewUsernameField().requestFocus();
                    return false;
                }else{
                }
            }
        } else {
            viewMyAccountPage.showMessage("Current username cannot be empty!");
            viewMyAccountPage.getUsernameField().requestFocus();
            return false;
        }
        usersDao.updateUsername(newUsername, currentUsername );
        viewMyAccountPage.showMessageSuccess("Username changed successfully");
        Session.getInstance().setAuthUserName(newUsername);
        // TODO check the result of update query
        return true;
    }

    public boolean validateChangeEmail() {
        String currentUsername = viewMyAccountPage.getUsernameField().getText();
        String newEmail = viewMyAccountPage.getNewEmailField().getText();

        List<Users> users = usersDao.findAll();

        if (!currentUsername.isEmpty()) {
                if (!newEmail.isEmpty()) {
                    if (!newEmail.matches(EMAIL_REGEX)) {
                        viewMyAccountPage.showMessage("Email format is not valid!");
                        viewMyAccountPage.getNewEmailField().setText("");
                        viewMyAccountPage.getNewEmailField().requestFocus();
                        return false;
                    }
                }else{
                    viewMyAccountPage.showMessage("Email cannot be empty!");
                }

        } else {
            viewMyAccountPage.showMessage("Current username cannot be empty!");
            viewMyAccountPage.getUsernameField().requestFocus();
            return false;
        }
        usersDao.updateEmail(newEmail, currentUsername);
        viewMyAccountPage.showMessageSuccess("Email changed successfully");
        Session.getInstance().setAuthEmail(newEmail);
        // TODO check the result of update query
        return true;
    }

    public boolean validateChangePassword(){
        String currentUser = viewChangePasswordPage.getCurrentUserField().getText();
        char [] input = viewChangePasswordPage.getNewPasswordField().getPassword();
        String newPassword = new String(input);
        String newPasswordConfirm = new String(viewChangePasswordPage.getConfirmNewPasswordField().getPassword());

        List <Users> users = usersDao.findAll();

        if (!currentUser.isEmpty()) {
            if (newPassword.length() < 6 ||
                    !newPassword.matches(UPPER_CASE_REGEX) ||
                    !newPassword.matches(LOWER_CASE_REGEX) ||
                    !newPassword.matches(NUMBERS_REGEX)) {
                viewChangePasswordPage.showMessage("Password should be more than 6 characters, " +
                        "contain at least one upper case character, contain at least one upper case character and " +
                        "contain at least one numeric character!");
                viewChangePasswordPage.getNewPasswordField().setText("");
                viewChangePasswordPage.getConfirmNewPasswordField().setText("");
                viewChangePasswordPage.getNewPasswordField().requestFocus();
                return false;
            }
            if(!newPasswordConfirm.equals(newPassword)) {
                viewChangePasswordPage.showMessage("Password confirmation must be identical to the password!");
                viewChangePasswordPage.getNewPasswordField().setText("");
                viewChangePasswordPage.getConfirmNewPasswordField().setText("");
                viewChangePasswordPage.getNewPasswordField().requestFocus();
                return false;
            }
        }else {
            viewChangePasswordPage.showMessage("Current username cannot be empty!");
            return false;
        }
        usersDao.updatePassword(newPassword, currentUser);
        viewChangePasswordPage.showMessageSuccess("Password changed successfully");
        return true;
    }
}

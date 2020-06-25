package Controller;

import Dao.UsersDao;
import Model.Users;
import View.RegisterPage;
import java.util.List;

public class RegisterController {

    private UsersDao usersDao;
    private RegisterPage viewRegisterPage;

    private static final String UPPER_CASE_REGEX ="(.*[A-Z].*)";
    private static final String LOWER_CASE_REGEX ="(.*[a-z].*)";
    private static final String NUMBERS_REGEX    = "(.*[0-9].*)";
    private static final String EMAIL_REGEX      = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    public RegisterController(RegisterPage view) {
        usersDao         = UsersDao.getInstance();
        viewRegisterPage = view;
    }

    public boolean validateAndRegisterUser() {
        String username      = viewRegisterPage.getUsernameField().getText();
        char[] input         = viewRegisterPage.getPasswordField().getPassword();
        String passwd        = new String(input);
        String passwdConfirm = new String(viewRegisterPage.getPasswordConfirmationField().getPassword());
        String email         = viewRegisterPage.getEmailField().getText();

            List<Users> users = usersDao.findAll();
            // validate username
            if(!username.isEmpty()) {
                for (Users temp : users) {
                    if (temp.getUsername().equals(username)) {
                        //user already exists
                        viewRegisterPage.showMessage("Username already taken, please select another one!");
                        viewRegisterPage.getUsernameField().setText("");
                        viewRegisterPage.getUsernameField().requestFocus();
                        return false;
                    }
                }
            } else {
                viewRegisterPage.showMessage("Username cannot be empty!");
                viewRegisterPage.getUsernameField().requestFocus();
                return false;
            }
            //validate password
            if (passwd.length() < 6 ||
                !passwd.matches(UPPER_CASE_REGEX) ||
                !passwd.matches(LOWER_CASE_REGEX) ||
                !passwd.matches(NUMBERS_REGEX)) {
                viewRegisterPage.showMessage("Password should be more than 6 characters, " +
                        "contain at least one upper case character, contain at least one upper case character and " +
                        "contain at least one numeric character!");
                viewRegisterPage.getPasswordField().setText("");
                viewRegisterPage.getPasswordField().requestFocus();
                return false;
            }
            // validate passwordConfirmation
            if(!passwd.equals(passwdConfirm)) {
                viewRegisterPage.showMessage("Password confirmation must be identical to the password!");
                viewRegisterPage.getPasswordConfirmationField().setText("");
                viewRegisterPage.getPasswordConfirmationField().requestFocus();
                return false;
            }
            // validate Email
            if (!email.matches(EMAIL_REGEX)) {
                viewRegisterPage.showMessage("Email format is not valid!");
                viewRegisterPage.getEmailField().setText("");
                viewRegisterPage.getEmailField().requestFocus();
                return false;
            }
            // everything ok, insert user
            usersDao.insert(username, passwd, email);
            return true;
    }
}
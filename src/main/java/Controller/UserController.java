package Controller;

import Dao.HistoryDao;
import Dao.UsersDao;
import Database.DatabaseConnection;
import Model.Audit;
import View.HistoryPage;

import java.sql.Connection;

public class UserController {

    private UsersDao usersDao;
    private HistoryDao historyDao;
    private HistoryPage viewHistoryPage;

    public UserController(HistoryPage view){
        Connection connection = DatabaseConnection.getConnection();
        usersDao = new UsersDao(connection);
        historyDao = new HistoryDao(connection);
        viewHistoryPage = view;
    }

}

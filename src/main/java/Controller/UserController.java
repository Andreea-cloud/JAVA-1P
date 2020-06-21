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
        usersDao = UsersDao.getInstance();
        historyDao = HistoryDao.getInstance();
        viewHistoryPage = view;
    }

}

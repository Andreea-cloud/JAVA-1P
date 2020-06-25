package Controller;

import Dao.HistoryDao;
import Dao.UsersDao;
import View.HistoryPage;

public class UserController {

    private UsersDao usersDao;
    private HistoryDao historyDao;
    private HistoryPage viewHistoryPage;

    public UserController(HistoryPage view){
        usersDao        = UsersDao.getInstance();
        historyDao      = HistoryDao.getInstance();
        viewHistoryPage = view;
    }
}
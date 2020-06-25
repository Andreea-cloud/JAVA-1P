package Controller;

import Dao.HistoryDao;

import java.util.Calendar;

public class HistoryController {
    private static HistoryController SINGLETON;

    public static final HistoryController getInstance(){
        if(SINGLETON == null){
            SINGLETON = new HistoryController();
        }
        return SINGLETON;
    }

    public static final void logAction(String actionType) {
        String usernameOrEmail      = Session.getInstance().getAuthUserName();
        java.util.Date currentTime  = Calendar.getInstance().getTime();
        HistoryDao.getInstance().insert(usernameOrEmail, actionType,  new java.sql.Timestamp(currentTime.getTime()));
    }
}
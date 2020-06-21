package Dao;

import Controller.Session;
import Database.DatabaseConnection;
import Model.Audit;
import Model.Users;
import lombok.Getter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Getter

public class HistoryDao {
    Connection connection;
    PreparedStatement insertQuery;
    PreparedStatement selectQuery;
    private static HistoryDao SINGLETON;

    private HistoryDao(){
        this.connection = DatabaseConnection.getConnection();
        try{
            insertQuery = connection.prepareStatement("INSERT INTO audit VALUES (null, ?,?,?)");
            selectQuery = connection.prepareStatement("SELECT * FROM audit WHERE username = ?");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static final HistoryDao getInstance(){
        if(SINGLETON == null){
            SINGLETON = new HistoryDao();
        }
        return SINGLETON;
    }

    public boolean insert(String username, String action, Timestamp timestamp ){
        try{
            insertQuery.setString(1, username);
            insertQuery.setString(2, action);
            insertQuery.setTimestamp(3,  timestamp);
            insertQuery.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Audit> findAll(){
        try {
            selectQuery.setString(1, Session.getInstance().getAuthUserName());
            ResultSet result = selectQuery.executeQuery();
            List<Audit> audit = new ArrayList<>();

            while(result.next()) {
                String username = result.getString("username");
                String action = result.getString("action");
                Timestamp timestamp = result.getTimestamp("timestamp");

                Audit history = new Audit(username, action, timestamp);
                audit.add(history);
            }

            return audit; // return the list
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}

package Dao;

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

    public HistoryDao(Connection connection){
        this.connection = connection;
        try{
            insertQuery = connection.prepareStatement("INSERT INTO audit-history VALUES (?,?,?)");
            selectQuery = connection.prepareStatement("SELECT * FROM audit-history");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insert(String username, String action, Time timestamp){
        try{
            insertQuery.setString(1, username);
            insertQuery.setString(2, action);
            insertQuery.setTime(3, timestamp);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Audit> findAll(){
        try {
            ResultSet result = selectQuery.executeQuery();
            List<Audit> audit = new ArrayList<>();

            while(result.next()) {
                String username = result.getString("username");
                String action = result.getString("action");
                Time timestamp = result.getTime("timestamp");

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

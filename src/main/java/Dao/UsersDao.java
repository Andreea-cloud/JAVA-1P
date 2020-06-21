package Dao;

import Database.DatabaseConnection;
import Model.Audit;
import Model.Users;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Getter
@Setter

public class UsersDao {

    Connection connection;
    PreparedStatement insertQuery;
    PreparedStatement selectAllQuery;
    PreparedStatement selectUserQuery;
    PreparedStatement updateUsernameQuery;
    PreparedStatement updateEmailQuery;
    PreparedStatement updatePasswordQuery;
    private static UsersDao SINGLETON;

    private UsersDao() {
        this.connection = DatabaseConnection.getConnection();
        try {
            insertQuery = connection.prepareStatement("INSERT INTO utilizatori VALUES (null, ?,?,?)");
            selectAllQuery = connection.prepareStatement("SELECT * FROM utilizatori");
            selectUserQuery = connection.prepareStatement("SELECT * FROM utilizatori WHERE username = ?");
            updateUsernameQuery = connection.prepareStatement("UPDATE utilizatori SET username = ? WHERE username = ?");
            updateEmailQuery = connection.prepareStatement("UPDATE utilizatori SET email = ? WHERE username = ?");
            updatePasswordQuery = connection.prepareStatement("UPDATE utilizatori SET password = ? WHERE username = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static final UsersDao getInstance(){
        if(SINGLETON == null){
            SINGLETON = new UsersDao();
        }
        return SINGLETON;
    }

    public boolean insert(String nameUser, String passwdUser, String emailUser ) {
        try {
            insertQuery.setString(1, nameUser);
            insertQuery.setString(2, passwdUser);
            insertQuery.setString(3, emailUser);
            int nameOfLinesModified = insertQuery.executeUpdate();
            return nameOfLinesModified != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

     public boolean updateUsername(String newUsername, String currentUsername ){
         try{
             updateUsernameQuery.setString(1, newUsername);
             updateUsernameQuery.setString(2, currentUsername);
             int nameOfLinesUpdateUser = updateUsernameQuery.executeUpdate();
             return nameOfLinesUpdateUser != 0;
         }catch(SQLException e){
             e.printStackTrace();
         }
         return false;
     }

    public boolean updateEmail(String newEmail, String currentUsername ){
        try{
            updateEmailQuery.setString(1, newEmail);
            updateEmailQuery.setString(2, currentUsername);
            int nameOfLinesUpdateEmail = updateEmailQuery.executeUpdate();
            return nameOfLinesUpdateEmail != 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePassword(String newPassword, String currentUsername ){
        try{
            updatePasswordQuery.setString(1, newPassword);
            updatePasswordQuery.setString(2, currentUsername);
            int nameOfLinesUpdatePassword = updatePasswordQuery.executeUpdate();
            return nameOfLinesUpdatePassword != 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Users> findAll() {
        try {
            ResultSet result = selectAllQuery.executeQuery();
            List<Users> users = new ArrayList<>();

            while(result.next()) {
                int id = result.getInt("id");
                String username = result.getString("username");
                String password = result.getString("password");
                String email = result.getString("email");

                Users user = new Users(id, username, password, email);
                users.add(user);
            }

            return users; // return the list
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}





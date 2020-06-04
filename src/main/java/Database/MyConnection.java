package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {

    public static void main(String[] args) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();


    }
}

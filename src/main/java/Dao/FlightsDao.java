package Dao;

import Model.Flights;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightsDao {

    Connection connection;
    PreparedStatement insertQuery;
    PreparedStatement selectQuery;

    public FlightsDao(Connection connection) {
        this.connection = connection;
        try {
            insertQuery = connection.prepareStatement("INSERT INTO zboruri VALUES (null, ?,?,?,?,?,?)");
            selectQuery = connection.prepareStatement("SELECT * FROM zboruri");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insert(String departure, String destination, LocalTime departureTime, LocalTime arrivalTime, String days, int price ) {
        try {
            insertQuery.setString(1, departure);
            insertQuery.setString(2, destination);
            insertQuery.setTime(3, Time.valueOf(departureTime));
            insertQuery.setTime(4, Time.valueOf(arrivalTime));
            insertQuery.setString(5, days);
            insertQuery.setInt(6, price);
            int nameOfLinesModified = insertQuery.executeUpdate();
            return nameOfLinesModified != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Flights> findAll() {
        try {
            ResultSet result = selectQuery.executeQuery();
            List<Flights> flights = new ArrayList<>();

            while(result.next()) {
                int id = result.getInt("ID");
                String departure = result.getString("Sursa");
                String destination = result.getString("Destinatie");
                LocalTime departureTime = result.getTime("Ora Plecare").toLocalTime();
                LocalTime arrivalTime = result.getTime("Ora Sosire").toLocalTime();
                String days = result.getString("Zile");
                int price = result.getInt("Pret");

                Flights Flight = new Flights(id, departure, destination, departureTime, arrivalTime, days, price);
                flights.add(Flight);
            }

            return flights; // return the list
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}

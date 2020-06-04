package Dao;

import Model.Flights;
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

    public boolean insert(String sursa, String destinatie, LocalTime oraPlecare, LocalTime oraSosire, String zile, int pret ) {
        try {
            insertQuery.setString(1, sursa);
            insertQuery.setString(2, destinatie);
            insertQuery.setTime(3, Time.valueOf(oraPlecare));
            insertQuery.setTime(4, Time.valueOf(oraSosire));
            insertQuery.setString(5, zile);
            insertQuery.setInt(6, pret);
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
                String sursa = result.getString("Sursa");
                String destinatie = result.getString("Destinatie");
                LocalTime oraPlecare = result.getTime("Ora Plecare").toLocalTime();
                LocalTime oraSosire = result.getTime("Ora Sosire").toLocalTime();
                String zile = result.getString("Zile");
                int pret = result.getInt("Pret");

                Flights Flight = new Flights(id, sursa, destinatie, oraPlecare, oraSosire, zile, pret);
                flights.add(Flight);
            }

            return flights; // return the list
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}

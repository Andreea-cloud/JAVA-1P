package Controller;

import Dao.FlightsDao;
import Dao.UsersDao;
import Database.DatabaseConnection;
import Model.Flights;
import Model.Users;

import java.sql.Connection;
import java.util.List;

public class MainController {

    private UsersDao usersDao;
    private FlightsDao flightsDao;

    public MainController() {
        Connection connection = DatabaseConnection.getConnection();
        usersDao = new UsersDao(connection);
        flightsDao = new FlightsDao(connection);
    }

    public boolean adaugaUser(String username, String password, String email) {
        return usersDao.insert(username, password, email);
    }

    public List<Users> getUsers() {return usersDao.findAll();

    }

//    public boolean adaugaZbor(String sursa, String destinatie, Time oraPlecare, Time oraSosire, String zile, int pret) {
//        return flightsDao.insert(sursa, destinatie, oraPlecare, oraSosire, zile, pret);
//    }

    public List<Flights> getFlights() {
        return flightsDao.findAll();
    }



}

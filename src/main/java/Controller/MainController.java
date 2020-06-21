package Controller;

import Dao.FlightsDao;
import Dao.UsersDao;
import Database.DatabaseConnection;
import Model.Flights;
import Model.Users;
import View.MainPage;

import java.sql.Connection;
import java.util.List;

public class MainController {

    private UsersDao usersDao;
    private FlightsDao flightsDao;

    public MainController() {
        usersDao = UsersDao.getInstance();
        flightsDao = FlightsDao.getInstance();
    }

    public boolean adaugaUser(String username, String password, String email) {
        return usersDao.insert(username, password, email);
    }

    public List<Users> getUsers() {return usersDao.findAll();

    }

    public List<Flights> getFlights() {
        return flightsDao.findAll();
    }



}

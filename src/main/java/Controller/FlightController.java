package Controller;

import Dao.FlightsDao;
import Database.DatabaseConnection;
import Model.Flights;
import View.AddFlightPage;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class FlightController {
    private FlightsDao flightsDao;
    private AddFlightPage viewAddFlightPage;

    public FlightController( AddFlightPage view){
        Connection connection = DatabaseConnection.getConnection();
        flightsDao = new FlightsDao(connection);
        viewAddFlightPage = view;
    }

    public boolean validateFlight(){

        String departure = viewAddFlightPage.getDepartureField().getText();
        String arrive    = viewAddFlightPage.getArriveField().getText();
        int price = 0;
       try {
           price = Integer.parseInt(viewAddFlightPage.getPriceField().getText());
       }catch(Exception e) {
           viewAddFlightPage.showMessage("The price format is invalid!Please use only number!");
           return false;
        }

        List<Flights> flights = flightsDao.findAll();

        if(departure.length() < 3){
            viewAddFlightPage.showMessage("Departure Flight should be more 3 characters!");
            viewAddFlightPage.getDepartureField().setText("");
            viewAddFlightPage.getDepartureField().requestFocus();
            return false;
        }

        if(arrive.length() < 3){
            viewAddFlightPage.showMessage("Destination Flight should be more 3 characters!");
            viewAddFlightPage.getDepartureField().setText("");
            viewAddFlightPage.getDepartureField().requestFocus();
            return false;
        }
//FIXME use select in DB
        for(Flights temp : flights){
            if(temp.getSursa().equals(departure)){
                if(temp.getDestinatie().equals(arrive)){
                    viewAddFlightPage.showMessage("This flight already exists! Please enter another flight!");
                    viewAddFlightPage.getDepartureField().setText("");
                    viewAddFlightPage.getArriveField().setText("");
                    viewAddFlightPage.getDepartureField().requestFocus();
                    return false;
                }
            }
        }

        LocalTime departureTime = LocalTime.parse(viewAddFlightPage.getDepartureTimePicker().toString());
        LocalTime flightTime    = LocalTime.parse(viewAddFlightPage.getTimeBox().toString());
        LocalTime arrivalTime   = departureTime.plusHours(flightTime.getHour())
                                             .plusMinutes(flightTime.getMinute());

        List<String> flightDaysCheckedList  = new ArrayList<String>();
        Component[] daysPanelComponents = viewAddFlightPage.getDaysPanel().getComponents();

        for (Component comp : daysPanelComponents) {
            if (comp instanceof JCheckBox) {
                JCheckBox box = (JCheckBox) comp;
                if( box.isSelected()) {
                    flightDaysCheckedList.add(box.getText());
                }
            }
        }
        if(flightDaysCheckedList.isEmpty()){
            viewAddFlightPage.showMessage("No days selected for flight!");
            return false;
        }

        String flightDaysCommaSeparated = String.join(", ", flightDaysCheckedList);

        if(price <= 0 ){
            viewAddFlightPage.showMessage("This flight has an incorrect price!");
            viewAddFlightPage.getPriceField().setText("");
            viewAddFlightPage.getPriceField().requestFocus();
            return false;
        }

        // everything ok, insert flight
        flightsDao.insert(departure, arrive, departureTime, arrivalTime, flightDaysCommaSeparated , price);
       return true;
    }
}

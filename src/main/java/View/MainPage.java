package View;

import Dao.FlightsDao;
import Database.DatabaseConnection;
import Model.Flights;
import lombok.Getter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
@Getter


public class MainPage extends MenuBarBase {

    private FlightsDao flightsDao;

    private JPanel mainPanel, currentPanel;
    private JButton addButton;
    private JTable flightsTable;
    private JLabel timeLabel, dateLabel;
    Timer timer;

    MainPage(String username, String email)
    {
        super(username, email);
        showFlights();
        initMainPage();
        timeDate();
    }

    MainPage() {
        super();
        showFlights();
        initMainPage();
        timeDate();
    }

    public void timeDate(){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                String time = timeFormat.format(date);
                timeLabel.setText("Time: " + time + ";                 ");

                Date date2 = new Date();
                DateFormat timeFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                String time2 = timeFormat2.format(date2);
                dateLabel.setText("             Date: " + time2 + ";");
                }
            };
            timer = new Timer(1000, actionListener);
            timer.setInitialDelay(0);
            timer.start();
        }

    private void initMainPage() {
        setTitle("Main Page");
        setSize(600, 600);
        setLocationRelativeTo(null);

        EmptyBorder border = new EmptyBorder(30, 45, 30, 45);

        timeLabel = new JLabel(" Time: ");
        timeLabel.setFont(new Font("Time New Roman", Font.BOLD, 20));
        timeLabel.setBounds(10, 12, 10, 10);

        dateLabel = new JLabel("Date: ");
        dateLabel.setFont(new Font("Time New Roman", Font.BOLD, 20));
        dateLabel.setBounds(10, 12, 10, 10);

        mainPanel = new JPanel();
        add(mainPanel);
        mainPanel.setBackground(Color.ORANGE);

        currentPanel = new JPanel();
        add(currentPanel, BorderLayout.NORTH);
        currentPanel.setBackground(Color.ORANGE);
        currentPanel.add(timeLabel, BorderLayout.WEST);
        currentPanel.add(dateLabel, BorderLayout.EAST);

        addButton = new JButton("Add new flight:");
        addButton.setBorder(border);
        addButton.setBackground(Color.WHITE);
        mainPanel.add(addButton, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.SOUTH);

        setVisible(true);

        addButton.addActionListener(e -> {
            AddFlightPage addFlight = new AddFlightPage();
            addFlight.setVisible(true);
            dispose();
        });

        // JScrollPane to have the table header
        add(new JScrollPane(flightsTable), BorderLayout.CENTER);
    }

    public void showFlights(){

        Connection connection = DatabaseConnection.getConnection();
        flightsDao = new FlightsDao(connection);
        DefaultTableModel model  = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Sursa");
        model.addColumn("Destinatie");
        model.addColumn("Ora Plecare");
        model.addColumn("Ora Sosire");
        model.addColumn("Zile");
        model.addColumn("Pret");
        flightsTable = new JTable(model);
        DefaultTableModel flightsModel = (DefaultTableModel)getFlightsTable().getModel();

        java.util.List<Flights> flightsList = flightsDao.findAll();
        Object[] row = new Object[7];
        for(int i = 0; i < flightsList.size(); ++i){
            row[0] = flightsList.get(i).getId();
            row[1] = flightsList.get(i).getSursa();
            row[2] = flightsList.get(i).getDestinatie();
            row[3] = flightsList.get(i).getOraPlecare();
            row[4] = flightsList.get(i).getOraSosire();
            row[5] = flightsList.get(i).getZile();
            row[6] = flightsList.get(i).getPret();
            flightsModel.addRow(row);
        }
    }
}

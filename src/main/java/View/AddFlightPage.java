package View;

import Controller.FlightController;
import Controller.HistoryController;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import lombok.Getter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalTime;

@Getter

public class AddFlightPage extends JFrame {

    private JPanel textPanel, optionsPanel, buttonPanel, daysPanel;
    private JButton cancelButton, addButton;
    private JLabel textLabel, departureLabel, arriveLabel, departureTimeLabel, timeLabel, daysLabel, priceLabel;
    private JTextField departureField, arriveField, priceField;
    private TimePicker departureTimePicker, timeBox;
    private JCheckBox mondayBox, tuesdayBox, wednesdayBox, thursdayBox, fridayBox, saturdayBox, sundayBox;

    AddFlightPage() {
        setTitle("Add new flight!");
        setFont(new Font("Time New Roman", Font.BOLD, 20));
        setSize(650, 550);
        setLocationRelativeTo(null);

        EmptyBorder border = new EmptyBorder(20, 10, 20, 5);

        textPanel = new JPanel();
        textPanel.setBackground(Color.orange);
        textLabel = new JLabel("Add new flight: ");
        textLabel.setFont(new Font("Time New Roman", Font.ITALIC, 25));
        textPanel.add(textLabel, BorderLayout.CENTER);
        add(textPanel, BorderLayout.NORTH);

        optionsPanel = new JPanel(new GridLayout(6, 2, 5, 15));
        optionsPanel.setBackground(Color.gray);

        departureLabel = new JLabel("Departure flight: ");
        departureLabel.setFont(new Font("Time New Roman", Font.BOLD, 20));
        departureLabel.setBorder(border);
        departureField = new JTextField();
        departureField.setBorder(border);

        arriveLabel = new JLabel("Destination flight");
        arriveLabel.setFont(new Font("Time New Roman", Font.BOLD, 20));
        arriveLabel.setBorder(border);
        arriveField = new JTextField();
        arriveField.setBorder(border);

        departureTimeLabel = new JLabel("Departure time: ");
        departureTimeLabel.setFont(new Font("Time New Roman", Font.BOLD, 20));
        departureTimeLabel.setBorder(border);
        TimePickerSettings timeSettings = new TimePickerSettings();
        timeSettings.use24HourClockFormat();
        timeSettings.initialTime = LocalTime.now();
        departureTimePicker = new TimePicker(timeSettings);

        timeLabel = new JLabel("Flight duration: ");
        timeLabel.setFont(new Font("Time New Roman", Font.BOLD, 20));
        timeLabel.setBorder(border);
        timeBox = new TimePicker(timeSettings);

        priceLabel = new JLabel("Price: ");
        priceLabel.setFont(new Font("Time New Roman", Font.BOLD, 20));
        priceLabel.setBorder(border);
        priceField = new JTextField();
        priceField.setBorder(border);

        daysLabel = new JLabel("Days: ");
        daysLabel.setFont(new Font("Time New Roman", Font.BOLD, 20));
        daysLabel.setBorder(border);
        mondayBox = new JCheckBox("Monday");
        tuesdayBox = new JCheckBox("Tuesday");
        wednesdayBox = new JCheckBox("Wednesday");
        thursdayBox = new JCheckBox("Thursday");
        fridayBox = new JCheckBox("Friday");
        saturdayBox = new JCheckBox("Saturday");
        sundayBox = new JCheckBox("Sunday");

        daysPanel = new JPanel();
        daysPanel.add(mondayBox);
        daysPanel.add(tuesdayBox);
        daysPanel.add(wednesdayBox);
        daysPanel.add(thursdayBox);
        daysPanel.add(fridayBox);
        daysPanel.add(saturdayBox);
        daysPanel.add(sundayBox);

        setVisible(true);

        optionsPanel.add(departureLabel);
        optionsPanel.add(departureField);
        optionsPanel.add(arriveLabel);
        optionsPanel.add(arriveField);
        optionsPanel.add(departureTimeLabel);
        optionsPanel.add(departureTimePicker);
        optionsPanel.add(timeLabel);
        optionsPanel.add(timeBox);
        optionsPanel.add(daysLabel);
        optionsPanel.add(daysPanel);
        optionsPanel.add(priceLabel);
        optionsPanel.add(priceField);

        add(optionsPanel, BorderLayout.CENTER);

        addButton = new JButton("Add flight ");
        addButton.setFont(new Font("Time New Roman", Font.ITALIC, 25));
        addButton.setBackground(Color.orange);
        cancelButton = new JButton("Cancel flight");
        cancelButton.setFont(new Font("Time New Roman", Font.ITALIC, 25));
        cancelButton.setBackground(Color.gray);

        buttonPanel = new JPanel();
        buttonPanel.add(addButton, BorderLayout.EAST);
        buttonPanel.add(cancelButton, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addButton.addActionListener(e -> {
            FlightController flightController = new FlightController(this);
            if (true == flightController.validateFlight()) {
                HistoryController.logAction("Add new flight!");
                MainPage mainPage = new MainPage();
                mainPage.setVisible(true);
                dispose();
            }
        });
        cancelButton.addActionListener(e -> {
            MainPage mainPage = new MainPage();
            HistoryController.logAction("Cancel new flight!");
            dispose();
        });
    }
    public static void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
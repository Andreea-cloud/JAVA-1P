package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class MainPage extends MenuBarBase {

    private JPanel mainPanel;
    private JButton addButton;

    MainPage(String username, String email)
    {
        super(username, email);
        initMainPage();
    }

    MainPage() {
        super();
        initMainPage();
    }

    private void initMainPage() {
        setTitle("Main Page");
        setSize(600, 600);
        setLocationRelativeTo(null);

        EmptyBorder border = new EmptyBorder(30, 45, 30, 45);

        mainPanel = new JPanel();
        add(mainPanel);
        mainPanel.setBackground(Color.ORANGE);

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
    }
}

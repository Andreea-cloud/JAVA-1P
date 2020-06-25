package View;

import Dao.HistoryDao;
import Model.Audit;
import lombok.Getter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collections;

@Getter

public class HistoryPage extends MenuBarBase {

    private HistoryDao historyDao;
    private JTable historyTable;

    HistoryPage() {
        super("", "");
        setTitle("History!");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        showHistory();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // JScrollPane to have the table header
        add(new JScrollPane(historyTable), BorderLayout.CENTER);
    }

    public void showHistory(){

        historyDao = HistoryDao.getInstance();
        DefaultTableModel model  = new DefaultTableModel();
        model.addColumn("username");
        model.addColumn("action");
        model.addColumn("timestamp");

        historyTable = new JTable(model);
        DefaultTableModel historyModel = (DefaultTableModel)getHistoryTable().getModel();

        java.util.List<Audit> historyList = historyDao.findAll();
        Collections.reverse(historyList);
        Object[] row = new Object[3];
        for(int i = 0; i < historyList.size(); ++i){
            row[0] = historyList.get(i).getUsername();
            row[1] = historyList.get(i).getAction();
            row[2] = historyList.get(i).getTimestamp();
            historyModel.addRow(row);
        }
    }
}
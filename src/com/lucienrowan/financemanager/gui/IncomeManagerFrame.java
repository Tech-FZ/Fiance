package com.lucienrowan.financemanager.gui;

import com.lucienrowan.financemanager.db.retrieval.SqliteTimeGroupRetrieval;
import com.lucienrowan.financemanager.objects.TimeGroup;

import javax.swing.*;
import java.awt.*;

public class IncomeManagerFrame extends JFrame {
    private JPanel incomeManagerPanel;
    private JTable incomeTable;
    private JButton createButton;
    private JButton editButton;
    private JButton deleteButton;

    private TimeGroup timeGroup;

    public IncomeManagerFrame(TimeGroup timeGroup) {
        setTitle("Fiancé - Income Manager");
        setContentPane(incomeManagerPanel);
        setMinimumSize(new Dimension(600, 300));
        setVisible(true);
    }

    private void setTimeGroup(TimeGroup timeGroup) {
        if (SqliteTimeGroupRetrieval.validateTimeGroup(timeGroup)) {
            this.timeGroup = timeGroup;
        }

        else {
            JOptionPane.showMessageDialog(this,
                    "This time group is not valid. Leaving income manager...");

            dispose();
        }
    }
}

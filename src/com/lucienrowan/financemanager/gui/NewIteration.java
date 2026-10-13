package com.lucienrowan.financemanager.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class NewIteration extends JFrame {
    private JComboBox cbMonth;
    private JSpinner sbYear;
    private JButton btnSave;
    private JButton btnCancel;
    private JPanel newIterationPanel;

    public NewIteration() {
        this.setContentPane(newIterationPanel);
        this.setTitle("Fiancé - Create New Iteration");
        sbYear.setValue(Calendar.getInstance().get(Calendar.YEAR));
        this.setMonthOfCbMonth();
        this.setMinimumSize(new Dimension(300, 200));
        this.setVisible(true);

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // save to database
                closeWindow();
            }
        });
    }

    private void setMonthOfCbMonth() {
        // default option
        int month = Calendar.getInstance().get(Calendar.MONTH);
        cbMonth.setSelectedIndex(month);
    }

    private void closeWindow() {
        dispose();
    }
}

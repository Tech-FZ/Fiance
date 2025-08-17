package com.lucienrowan.financemanager.gui;

import com.lucienrowan.financemanager.objects.Months;
import com.lucienrowan.financemanager.objects.TimeGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class TimeGroupFrame extends JFrame {
    private JPanel timeGroupPanel;
    private JComboBox cbMonth;
    private JSpinner sbYear;
    private JButton btnContinue;

    public TimeGroupFrame() {
        this.setContentPane(timeGroupPanel);
        sbYear.setValue(Calendar.getInstance().get(Calendar.YEAR));
        this.setTitle("Fiancé - Select Time Frame");
        this.setMonthOfCbMonth();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300, 200));
        this.setVisible(true);

        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                continueWithSelectedTimeGroup();
            }
        });
    }

    private void setMonthOfCbMonth() {
        // TODO: Make the program ask the database for the latest TimeGroup entry

        // fallback option (currently default as database functionality is not implemented yet)
        int month = Calendar.getInstance().get(Calendar.MONTH);
        cbMonth.setSelectedIndex(month);
    }

    private void continueWithSelectedTimeGroup() {
        TimeGroup timeGroup = new TimeGroup((Integer) sbYear.getValue(), Months.values()[cbMonth.getSelectedIndex()]);

        // TODO: Check if said time group exists in database
        // TODO: If it exists, make user select revision (or create new one)
        // TODO: If it does not exist, ask user if time group should be created

        JOptionPane.showMessageDialog(this, "You would now continue, but that isn't implemented yet.",
                "Functionality not implemented", JOptionPane.PLAIN_MESSAGE); // temporary
    }
}

package com.lucienrowan.financemanager.gui;

import com.lucienrowan.financemanager.core.PlatformDetector;
import com.lucienrowan.financemanager.db.SqliteInsert;
import com.lucienrowan.financemanager.db.SqliteManager;
import com.lucienrowan.financemanager.db.SqliteRetrieval;
import com.lucienrowan.financemanager.objects.TimeGroup;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.sql.Connection;
import java.util.LinkedList;

public class TgIterationFrame extends JFrame {
    private JPanel tgIterationPanel;
    private JTable iterationTable;
    private JButton selectBtn;
    private JButton newBtn;
    private LinkedList<Object[]> timeGroups;
    private LinkedList<TimeGroup> dbTimeGroups;
    private TimeGroup filterTimeGroup;

    public TgIterationFrame() {
        initialiseTgIterationFrame();
        this.retrieveTimeGroups();
        addEventListeners();
    }

    public TgIterationFrame(TimeGroup filterTimeGroup) {
        initialiseTgIterationFrame();
        this.filterTimeGroup = filterTimeGroup;
        this.retrieveTimeGroups();
        addEventListeners();
    }

    private void addEventListeners() {
        newBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewIteration newIteration = new NewIteration();

                newIteration.addWindowStateListener(new WindowStateListener() {
                    public void windowClosed(WindowEvent e) {
                        retrieveTimeGroups();
                    }

                    public void windowStateChanged(WindowEvent e) {
                        if (e.getNewState() == WindowEvent.WINDOW_CLOSED) {
                            retrieveTimeGroups();
                        }
                    }
                });
            }
        });
    }

    private void initialiseTgIterationFrame() {
        this.setContentPane(tgIterationPanel);
        this.setTitle("Fiancé - Select Iteration");
        this.setMinimumSize(new Dimension(300, 200));
        this.pack();
        this.setVisible(true);
    }

    private void retrieveTimeGroups() {
        if (filterTimeGroup == null) {
            dbTimeGroups = SqliteRetrieval.retrieveTimeGroups();
        }

        else {
            dbTimeGroups = SqliteRetrieval.retrieveTimeGroups(filterTimeGroup);
        }

        if (dbTimeGroups.isEmpty()) {
            // Create an iteration of the current month
            SqliteInsert.insertTimeGroup(filterTimeGroup);
        }

        else {
            timeGroups = new LinkedList<Object[]>();

            for (TimeGroup timeGroup : dbTimeGroups) {
                Object[] objTimeGroup = {timeGroup.getYear(), timeGroup.getMonth(), timeGroup.getIteration()};
                timeGroups.add(objTimeGroup);
            }

            // Make table model for iteration
            Object[] columnNames = {"Year", "Month", "Iteration"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            for (int i = 0; i < timeGroups.size(); i++) {
                tableModel.addRow(timeGroups.get(i));
            }

            iterationTable.setModel(tableModel);
        }

    }

    private void prepareColumns() {
        String[] columnNames = {"Year", "Month", "Iteration"};
        this.iterationTable = new JTable(getTimeGroupArray(), columnNames);
        iterationTable.setFillsViewportHeight(true);
        iterationTable.setDefaultEditor(Object.class, null);
    }

    public Object[][] getTimeGroupArray() {
        try {
            if (timeGroups == null) {
                JOptionPane.showMessageDialog(null,
                        "There are no time groups since they are null.",
                        "Notice", JOptionPane.INFORMATION_MESSAGE);

                return new Object[1][3];
            }

            Object[][] timeGroupArray = new Object[timeGroups.toArray().length][3];

            for (int i = 0; i < timeGroups.toArray().length; i++) {
                timeGroupArray[i] = timeGroups.get(i);
            }

            return timeGroupArray;
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage() + " This program will terminate.", "Critical error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(e.hashCode());
        }

        return new Object[1][3];
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        prepareColumns();
    }
}

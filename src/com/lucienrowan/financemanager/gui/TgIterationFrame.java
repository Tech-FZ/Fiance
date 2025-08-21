package com.lucienrowan.financemanager.gui;

import com.lucienrowan.financemanager.core.PlatformDetector;
import com.lucienrowan.financemanager.db.SqliteManager;
import com.lucienrowan.financemanager.db.SqliteRetrieval;
import com.lucienrowan.financemanager.objects.TimeGroup;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
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
    }

    public TgIterationFrame(TimeGroup filterTimeGroup) {
        initialiseTgIterationFrame();
        this.filterTimeGroup = filterTimeGroup;
        this.retrieveTimeGroups();
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
            // TODO: Create an iteration of the current month
        }

        else {
            timeGroups = new LinkedList<Object[]>();

            for (TimeGroup timeGroup : dbTimeGroups) {
                Object[] objTimeGroup = {timeGroup.getYear(), timeGroup.getMonth(), timeGroup.getIteration()};
                timeGroups.add(objTimeGroup);
            }
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

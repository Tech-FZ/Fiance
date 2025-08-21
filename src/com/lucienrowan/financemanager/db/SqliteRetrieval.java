package com.lucienrowan.financemanager.db;

import com.lucienrowan.financemanager.core.PlatformDetector;
import com.lucienrowan.financemanager.objects.Months;
import com.lucienrowan.financemanager.objects.TimeGroup;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class SqliteRetrieval {
    public static LinkedList<TimeGroup> retrieveTimeGroups() {
        Connection connection = SqliteManager.getConnection(PlatformDetector.getFilePath());
        LinkedList<TimeGroup> timeGroups = new LinkedList<>();

        String query = "SELECT * FROM TimeGroup";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            // process the result set
            while (resultSet.next()) {
                TimeGroup timeGroup = new TimeGroup(resultSet.getInt("id"), resultSet.getInt("year"),
                        Months.values()[resultSet.getInt("month")], resultSet.getInt("iteration"));

                timeGroups.add(timeGroup);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "SQLite Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage() + " This program will terminate.", "Critical error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(e.hashCode());
        }

        return timeGroups; // temporary
    }

    public static LinkedList<TimeGroup> retrieveTimeGroups(TimeGroup filterTimeGroup) {
        Connection connection = SqliteManager.getConnection(PlatformDetector.getFilePath());
        LinkedList<TimeGroup> timeGroups = new LinkedList<>();

        // We only filter based on the given month and year. We do not filter according to the id and/or the iteration.
        String query = "SELECT * FROM TimeGroup WHERE year = " + filterTimeGroup.getYear() + " AND month = " +
                filterTimeGroup.getMonth();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            // process the result set
            while (resultSet.next()) {
                TimeGroup timeGroup = new TimeGroup(resultSet.getInt("id"), resultSet.getInt("year"),
                        Months.values()[resultSet.getInt("month")], resultSet.getInt("iteration"));

                timeGroups.add(timeGroup);
            }
        } catch (SQLException e) {
            // In this case, an SQL exception should not be that fatal.
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "SQLite Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Unknown exceptions are always treated as critical.
            JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage() + " This program will terminate.", "Critical error",
                    JOptionPane.ERROR_MESSAGE);

            // and therefore, they end the process.
            System.exit(e.hashCode());
        }

        return timeGroups; // temporary
    }
}

package com.lucienrowan.financemanager.db.retrieval;

import com.lucienrowan.financemanager.core.PlatformDetector;
import com.lucienrowan.financemanager.db.SqliteManager;
import com.lucienrowan.financemanager.objects.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteIncomeRetrieval {
    public static double retrieveIncomeSum(TimeGroup timeGroup) {
        Connection connection = SqliteManager.getConnection(PlatformDetector.getFilePath());

        String query = "SELECT SUM(amount) AS totalAmount FROM Income " +
                "WHERE timeGroupId = " + timeGroup.getId() + ";";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            // process the result set
            while (resultSet.next()) {
                double totalIncome = resultSet.getDouble("totalAmount");
                return totalIncome;
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

        return 0;
    }
}

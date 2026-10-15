package com.lucienrowan.financemanager.db.retrieval;

import com.lucienrowan.financemanager.core.PlatformDetector;
import com.lucienrowan.financemanager.db.SqliteManager;
import com.lucienrowan.financemanager.objects.BudgetPercentages;
import com.lucienrowan.financemanager.objects.TimeGroup;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class SqliteBudgetPercentageRetrieval {
    public static LinkedList<BudgetPercentages> retrieveBudgetPercentages(TimeGroup timeGroup) {
        Connection connection = SqliteManager.getConnection(PlatformDetector.getFilePath());
        LinkedList<BudgetPercentages> budgetPercentagesList = new LinkedList<>();

        String query = "SELECT * FROM BudgetPercentages " +
                "WHERE timeGroupId = " + timeGroup.getId() + ";";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                BudgetPercentages budgetPercentage = new BudgetPercentages(
                        resultSet.getInt("timeGroupId"), resultSet.getInt("financeTypeId"),
                        resultSet.getDouble("percentage"), resultSet.getInt("id"));

                budgetPercentagesList.add(budgetPercentage);
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

        return budgetPercentagesList;
    }
}

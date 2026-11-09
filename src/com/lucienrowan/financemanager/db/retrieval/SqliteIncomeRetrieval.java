package com.lucienrowan.financemanager.db.retrieval;

import com.lucienrowan.financemanager.core.PlatformDetector;
import com.lucienrowan.financemanager.db.SqliteManager;
import com.lucienrowan.financemanager.objects.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

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

    public static LinkedList<Income> retrieveIncome(TimeGroup timeGroup) {
        Connection connection = SqliteManager.getConnection(PlatformDetector.getFilePath());
        LinkedList<Income> incomeList = new LinkedList<Income>();

        String query = "SELECT * FROM Income " +
                "WHERE timeGroupId = " + timeGroup.getId() + ";";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            // process the result set
            while (resultSet.next()) {
                Income income = new Income(resultSet.getString("description"),
                        resultSet.getDouble("amount"), resultSet.getInt("timeGroupId"),
                        resultSet.getInt("id"));

                incomeList.add(income);
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

        return incomeList;
    }
}

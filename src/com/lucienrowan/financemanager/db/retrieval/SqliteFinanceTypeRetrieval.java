package com.lucienrowan.financemanager.db.retrieval;

import com.lucienrowan.financemanager.core.PlatformDetector;
import com.lucienrowan.financemanager.db.SqliteManager;
import com.lucienrowan.financemanager.objects.FinanceType;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class SqliteFinanceTypeRetrieval {
    public static LinkedList<FinanceType> retrieveFinanceTypes() {
        Connection connection = SqliteManager.getConnection(PlatformDetector.getFilePath());
        LinkedList<FinanceType> financeTypes = new LinkedList<>();

        String query = "SELECT * FROM FinanceType;";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            // process the result set
            while (resultSet.next()) {
                FinanceType financeType = new FinanceType(resultSet.getInt("id"),
                        resultSet.getString("name"));

                financeTypes.add(financeType);
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

        return financeTypes;
    }

    public static FinanceType retrieveFinanceType(int id) {
        Connection connection = SqliteManager.getConnection(PlatformDetector.getFilePath());
        FinanceType financeType = null;

        String query = "SELECT TOP(1) * FROM FinanceType WHERE id = " + id + ";";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            // process the result set
            while (resultSet.next()) {
                financeType = new FinanceType(resultSet.getInt("id"),
                        resultSet.getString("name"));
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

        return financeType;
    }
}

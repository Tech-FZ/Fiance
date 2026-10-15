package com.lucienrowan.financemanager.db;

import javax.swing.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteManager {
    public static Connection getConnection(String filePath) {
        try {
            File file = new File(filePath);

            if (file.exists() && file.isDirectory()) {
                filePath += "/fiance_db.sqlite";
                file = new File(filePath);
            }

            if (file.createNewFile()) {
                // TODO: Report that file was created successfully
            }

            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);
            CreateTablesIfNonexistent(connection);
            return connection; // for further use
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage() + " This program will terminate.", "Error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(e.getErrorCode());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage() + " This program will terminate.", "Critical error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(e.hashCode());
        }

        return null;
    }

    public static void CreateTablesIfNonexistent(Connection connection) {
        CreateTimeGroupTableIfNonexistent(connection);
        CreateFinanceTypeTableIfNonexistent(connection);
        createIncomeTableIfNonexistent(connection);
        createBudgetPercentagesTableIfNonexistent(connection);
    }

    private static void CreateTimeGroupTableIfNonexistent(Connection connection) {
        String sql = "CREATE TABLE IF NOT EXISTS TimeGroup (" +
                     " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     " \"year\" INTEGER NOT NULL," +
                     " \"month\" INTEGER NOT NULL CHECK (\"month\" >= 0 & \"month\" <= 11)," +
                     " iteration INTEGER NOT NULL CHECK (iteration >= 0)" +
                     ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage() + " This program will terminate.", "Error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(e.getErrorCode());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage() + " This program will terminate.", "Critical error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(e.hashCode());
        }
    }

    private static void CreateFinanceTypeTableIfNonexistent(Connection connection) {
        String sql = "CREATE TABLE IF NOT EXISTS FinanceType (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " \"name\" TEXT NOT NULL UNIQUE" +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage() + " This program will terminate.", "Error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(e.getErrorCode());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage() + " This program will terminate.", "Critical error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(e.hashCode());
        }
    }

    private static void createIncomeTableIfNonexistent(Connection connection) {
        String sql = "CREATE TABLE IF NOT EXISTS Income (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " \"description\" TEXT NULL," +
                " amount DECIMAL NOT NULL," +
                " timeGroupId INTEGER NOT NULL," +
                " FOREIGN KEY (timeGroupId) REFERENCES TimeGroup(id)" +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage() + " This program will terminate.", "Error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(e.getErrorCode());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage() + " This program will terminate.", "Critical error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(e.hashCode());
        }
    }

    private static void createBudgetPercentagesTableIfNonexistent(Connection connection) {
        String sql = "CREATE TABLE IF NOT EXISTS BudgetPercentages (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " \"financeTypeId\" INTEGER NOT NULL," +
                " percentage DECIMAL NOT NULL CHECK (" +
                "   percentage > 0 AND percentage <= 1)," +
                " timeGroupId INTEGER NOT NULL," +
                " FOREIGN KEY (financeTypeId) REFERENCES FinanceType(id)," +
                " FOREIGN KEY (timeGroupId) REFERENCES TimeGroup(id)" +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage() + " This program will terminate.", "Error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(e.getErrorCode());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage() + " This program will terminate.", "Critical error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(e.hashCode());
        }
    }
}

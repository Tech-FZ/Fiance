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
    }

    private static void CreateTimeGroupTableIfNonexistent(Connection connection) {
        String sql = "CREATE TABLE IF NOT EXISTS TimeGroup (" +
                     " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     " year INTEGER NOT NULL," +
                     " month INTEGER NOT NULL CHECK (month >= 0 & month <= 11)," +
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
}

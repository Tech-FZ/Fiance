package com.lucienrowan.financemanager.db;

import javax.swing.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

            return DriverManager.getConnection("jdbc:sqlite:" + filePath);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(e.getErrorCode());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getLocalizedMessage() + "This program will terminate", "Critical error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(e.hashCode());
        }

        return null;
    }
}

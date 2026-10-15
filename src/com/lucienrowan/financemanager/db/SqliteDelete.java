package com.lucienrowan.financemanager.db;

import com.lucienrowan.financemanager.core.PlatformDetector;
import com.lucienrowan.financemanager.objects.FinanceType;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteDelete {
    public static void deleteFinanceType(FinanceType financeType) {
        Connection connection = SqliteManager.getConnection(PlatformDetector.getFilePath());
        String query = "DELETE FROM FinanceType WHERE \"id\" = " + financeType.getId() + ";";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
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
    }
}

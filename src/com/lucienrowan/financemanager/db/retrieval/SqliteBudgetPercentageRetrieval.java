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
    public static LinkedList<BudgetPercentages> retrieveBudgetPercentages(TimeGroup timeGroup) throws SQLException {
        Connection connection = SqliteManager.getConnection(PlatformDetector.getFilePath());
        LinkedList<BudgetPercentages> budgetPercentagesList = new LinkedList<>();

        String query = "SELECT * FROM BudgetPercentages " +
                "WHERE timeGroupId = " + timeGroup.getId() + ";";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            BudgetPercentages budgetPercentage = new BudgetPercentages(
                    resultSet.getInt("timeGroupId"), resultSet.getInt("financeTypeId"),
                    resultSet.getDouble("percentage"), resultSet.getInt("id"));

            budgetPercentagesList.add(budgetPercentage);
        }

        return budgetPercentagesList;
    }
}

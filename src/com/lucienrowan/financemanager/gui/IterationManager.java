package com.lucienrowan.financemanager.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.lucienrowan.financemanager.db.retrieval.SqliteBudgetPercentageRetrieval;
import com.lucienrowan.financemanager.db.retrieval.SqliteIncomeRetrieval;
import com.lucienrowan.financemanager.db.retrieval.SqliteTimeGroupRetrieval;
import com.lucienrowan.financemanager.objects.*;

import java.awt.*;
import java.util.LinkedList;

public class IterationManager extends JFrame {
    private JPanel iterationManagerPanel;
    private JTextField incomeTextField;
    private JButton manageIncomeBtn;
    private JTable budgetTable;
    private JButton deleteBudgetButton;
    private JButton manageBudgetButton;
    private JButton createBudgetButton;
    private double totalIncome;

    private TimeGroup timeGroup;

    public IterationManager(TimeGroup timeGroup) {
        setContentPane(iterationManagerPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setTitle("Fiancé - " + timeGroup.getMonth() + " " + timeGroup.getYear() +
                ", Iteration " + timeGroup.getIteration());

        setTimeGroup(timeGroup);
        setMinimumSize(new Dimension(600, 400));
        retrieveData();
        setVisible(true);
    }

    private void setTimeGroup(TimeGroup timeGroup) {
        if (SqliteTimeGroupRetrieval.validateTimeGroup(timeGroup)) {
            this.timeGroup = timeGroup;
        }

        else {
            JOptionPane.showMessageDialog(this,
                    "This time group is not valid. Leaving iteration manager...");

            dispose();
        }
    }

    private void retrieveData() {
        retrieveTotalIncome();
        retrieveBudgets();
    }

    private void retrieveTotalIncome() {
        totalIncome = SqliteIncomeRetrieval.retrieveIncomeSum(timeGroup);
        incomeTextField.setText(String.valueOf(totalIncome));
    }

    private void retrieveBudgets() {
        LinkedList<BudgetPercentages> budgetPercentagesList =
                SqliteBudgetPercentageRetrieval.retrieveBudgetPercentages(timeGroup);

        Object[] columns = new Object[]{"Finance Type", "Percentage", "Amount"};
        Object[][] rows;

        double totalPercentage = 0;

        for (int i = 0; i < budgetPercentagesList.size(); i++) {
            totalPercentage += budgetPercentagesList.get(i).getPercentage();
        }

        if (totalPercentage == 1) {
            rows = new Object[budgetPercentagesList.size()][columns.length];
        }

        else {
            rows = new Object[budgetPercentagesList.size() + 1][columns.length];
        }

        for (int i = 0; i < budgetPercentagesList.size(); i++) {
            rows[i][0] = budgetPercentagesList.get(i).getFinanceType().getName();
            rows[i][1] = budgetPercentagesList.get(i).getPercentage();
            rows[i][2] = totalIncome * budgetPercentagesList.get(i).getPercentage();
        }

        if (totalPercentage < 1) {
            rows[rows.length - 1][0] = "Remaining";
            rows[rows.length - 1][1] = 1 - totalPercentage;
            rows[rows.length - 1][2] = totalIncome * (1 - totalPercentage);
        }

        DefaultTableModel tableModel = new DefaultTableModel(rows, columns);

        budgetTable.setDefaultEditor(Object.class, null);
        budgetTable.setModel(tableModel);
    }
}

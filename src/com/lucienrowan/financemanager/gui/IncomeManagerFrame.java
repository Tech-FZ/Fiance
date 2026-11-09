package com.lucienrowan.financemanager.gui;

import com.lucienrowan.financemanager.db.retrieval.SqliteBudgetPercentageRetrieval;
import com.lucienrowan.financemanager.db.retrieval.SqliteIncomeRetrieval;
import com.lucienrowan.financemanager.db.retrieval.SqliteTimeGroupRetrieval;
import com.lucienrowan.financemanager.objects.BudgetPercentages;
import com.lucienrowan.financemanager.objects.Income;
import com.lucienrowan.financemanager.objects.TimeGroup;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

public class IncomeManagerFrame extends JFrame {
    private JPanel incomeManagerPanel;
    private JTable incomeTable;
    private JButton createButton;
    private JButton editButton;
    private JButton deleteButton;

    private TimeGroup timeGroup;

    public IncomeManagerFrame(TimeGroup timeGroup) {
        setTitle("Fiancé - Income Manager");
        setTimeGroup(timeGroup);
        setContentPane(incomeManagerPanel);
        setMinimumSize(new Dimension(600, 300));
        retrieveIncome();
        setVisible(true);
    }

    private void setTimeGroup(TimeGroup timeGroup) {
        if (SqliteTimeGroupRetrieval.validateTimeGroup(timeGroup)) {
            this.timeGroup = timeGroup;
        }

        else {
            JOptionPane.showMessageDialog(this,
                    "This time group is not valid. Leaving income manager...");

            dispose();
        }
    }

    private void retrieveIncome() {
        LinkedList<Income> incomeList = SqliteIncomeRetrieval.retrieveIncome(timeGroup);

        Object[] columns = new Object[]{"Description", "Amount"};
        Object[][] rows = new Object[incomeList.size()][columns.length];

        for (int i = 0; i < incomeList.size(); i++) {
            rows[i][0] = incomeList.get(i).getDescription();
            rows[i][1] = incomeList.get(i).getAmount();
        }

        DefaultTableModel tableModel = new DefaultTableModel(rows, columns);
        incomeTable.setDefaultEditor(Object.class, null);
        incomeTable.setModel(tableModel);
    }
}

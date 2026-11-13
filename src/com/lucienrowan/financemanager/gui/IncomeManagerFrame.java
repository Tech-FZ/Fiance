package com.lucienrowan.financemanager.gui;

import com.lucienrowan.financemanager.db.retrieval.SqliteIncomeRetrieval;
import com.lucienrowan.financemanager.db.retrieval.SqliteTimeGroupRetrieval;
import com.lucienrowan.financemanager.events.ISubscriber;
import com.lucienrowan.financemanager.events.args.IncomeDatabaseEvent;
import com.lucienrowan.financemanager.objects.DatabaseMode;
import com.lucienrowan.financemanager.objects.Income;
import com.lucienrowan.financemanager.objects.TimeGroup;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class IncomeManagerFrame extends JFrame implements ISubscriber<IncomeDatabaseEvent> {
    private JPanel incomeManagerPanel;
    private JTable incomeTable;
    private JButton createButton;
    private JButton editButton;
    private JButton deleteButton;
    private LinkedList<Income> incomeList;

    private TimeGroup timeGroup;

    public IncomeManagerFrame(TimeGroup timeGroup) {
        setTitle("Fiancé - Income Manager");
        setTimeGroup(timeGroup);
        setContentPane(incomeManagerPanel);
        setMinimumSize(new Dimension(600, 300));
        retrieveIncome();
        setVisible(true);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateIncomeFrame cif = new CreateIncomeFrame(timeGroup);
                cif.getPublisher().subscribe(IncomeManagerFrame.this);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Get selected income entry
                CreateIncomeFrame cif = new CreateIncomeFrame(timeGroup);
            }
        });
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
        incomeList = SqliteIncomeRetrieval.retrieveIncome(timeGroup);
        prepareTable();
    }

    private void prepareTable() {
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

    @Override
    public void doOperation(IncomeDatabaseEvent e) {
        if (e.getDatabaseMode() == DatabaseMode.DELETE) {
            int incomeIndex = incomeList.indexOf(e.getIncome());

            if (incomeIndex >= 0 && incomeIndex < incomeList.size()) {
                incomeTable.remove(incomeIndex);
            }
        }

        else if (e.getDatabaseMode() == DatabaseMode.INSERT) {
            incomeList.add(e.getIncome());
            prepareTable();
        }

        e.getPublisher().unsubscribe(IncomeManagerFrame.this);
    }
}

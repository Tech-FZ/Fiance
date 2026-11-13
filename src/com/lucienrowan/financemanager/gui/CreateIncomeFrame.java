package com.lucienrowan.financemanager.gui;

import com.lucienrowan.financemanager.db.SqliteInsert;
import com.lucienrowan.financemanager.db.retrieval.SqliteTimeGroupRetrieval;
import com.lucienrowan.financemanager.events.args.IncomeDatabaseEvent;
import com.lucienrowan.financemanager.events.publishers.BasePublisher;
import com.lucienrowan.financemanager.objects.DatabaseMode;
import com.lucienrowan.financemanager.objects.Income;
import com.lucienrowan.financemanager.objects.TimeGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CreateIncomeFrame extends JFrame {
    private JPanel createIncomePanel;
    private JTextField descField;
    private JTextField amountField;
    private JButton saveButton;
    private JButton cancelButton;
    private DecimalFormat df = new DecimalFormat("0.00");
    private Income income;
    private TimeGroup timeGroup;
    private BasePublisher<IncomeDatabaseEvent> publisher = new BasePublisher<>();

    public CreateIncomeFrame(TimeGroup timeGroup) {
        initialiseWindow(timeGroup);
    }

    public CreateIncomeFrame(TimeGroup timeGroup, Income income) {
        this.income = income;
        initialiseWindow(timeGroup);
        descField.setText(income.getDescription());
        amountField.setText(String.valueOf(income.getAmount()));
    }

    private void initialiseWindow(TimeGroup timeGroup) {
        setTitle("Fiancé - Create Income");
        setMinimumSize(new Dimension(400, 250));
        setContentPane(createIncomePanel);
        setVisible(true);
        setTimeGroup(timeGroup);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                IncomeDatabaseEvent ide;

                if (income == null) {
                    income = new Income(descField.getText(), amount, timeGroup.getId());
                    SqliteInsert.insertIncome(income);
                    ide = new IncomeDatabaseEvent(publisher, income, DatabaseMode.INSERT);
                }

                else {
                    income.setAmount(amount);
                    income.setDescription(descField.getText());
                    income.setTimeGroupId(timeGroup.getId());
                    ide = new IncomeDatabaseEvent(publisher, income, DatabaseMode.UPDATE);
                }

                publisher.publish(ide);
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void setTimeGroup(TimeGroup timeGroup) {
        if (SqliteTimeGroupRetrieval.validateTimeGroup(timeGroup)) {
            this.timeGroup = timeGroup;
        }

        else {
            JOptionPane.showMessageDialog(this,
                    "This time group is not valid. Leaving income editor...");

            dispose();
        }
    }

    public BasePublisher<IncomeDatabaseEvent> getPublisher() {
        return publisher;
    }
}

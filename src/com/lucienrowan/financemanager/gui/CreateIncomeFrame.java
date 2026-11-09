package com.lucienrowan.financemanager.gui;

import com.lucienrowan.financemanager.objects.Income;

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

    public CreateIncomeFrame() {
        initialiseWindow();
    }

    public CreateIncomeFrame(Income income) {
        this.income = income;
        initialiseWindow();
        descField.setText(income.getDescription());
        amountField.setText(String.valueOf(income.getAmount()));
    }

    private void initialiseWindow() {
        setTitle("Fiancé - Create Income");
        setMinimumSize(new Dimension(400, 250));
        setContentPane(createIncomePanel);
        setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

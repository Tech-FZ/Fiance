package com.lucienrowan.financemanager.gui;

import com.lucienrowan.financemanager.db.SqliteInsert;
import com.lucienrowan.financemanager.db.retrieval.SqliteFinanceTypeRetrieval;
import com.lucienrowan.financemanager.db.retrieval.SqliteTimeGroupRetrieval;
import com.lucienrowan.financemanager.gui.listcellrenderers.FinanceTypeListCellRenderer;
import com.lucienrowan.financemanager.objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class CreateBudgetFrame extends JFrame {
    private JPanel createBudgetPanel;
    private JComboBox financeTypeComboBox;
    private JTextField percentageTextField;
    private JButton saveButton;
    private JButton cancelButton;
    private TimeGroup timeGroup;
    private LinkedList<FinanceType> financeTypeList;

    public CreateBudgetFrame(TimeGroup timeGroup) {
        setContentPane(createBudgetPanel);
        setTitle("Fiancé - Create Budget");
        setMinimumSize(new Dimension(400, 250));
        setVisible(true);
        setTimeGroup(timeGroup);
        financeTypeComboBox.setRenderer(new FinanceTypeListCellRenderer("getName"));
        prepareFinanceTypes();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BudgetPercentages budgetPercentages = new BudgetPercentages(timeGroup.getId(),
                            ((FinanceType) financeTypeComboBox.getSelectedItem()).getId(),
                            Double.parseDouble(percentageTextField.getText()));

                    SqliteInsert.insertBudgetPercentage(budgetPercentages);

                    dispose();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(CreateBudgetFrame.this,
                            ex.getMessage() + "\nPlease check your input.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

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
                    "This time group is not valid. Leaving budget creator...");

            dispose();
        }
    }

    private void prepareFinanceTypes() {
        financeTypeList = SqliteFinanceTypeRetrieval.retrieveFinanceTypes();

        if (!financeTypeList.isEmpty()) {

            for (FinanceType financeType : financeTypeList) {
                financeTypeComboBox.addItem(financeType);
            }
        }

        else  {
            JOptionPane.showMessageDialog(this,
                    "There are no finance types. Please create one using the finance type dialogues in " +
                    "the main menu. This window will be closed...", "Error",  JOptionPane.ERROR_MESSAGE);

            dispose();
        }
    }


}

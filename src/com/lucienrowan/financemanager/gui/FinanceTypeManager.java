package com.lucienrowan.financemanager.gui;

import com.lucienrowan.financemanager.db.SqliteDelete;
import com.lucienrowan.financemanager.db.SqliteInsert;
import com.lucienrowan.financemanager.db.SqliteUpdate;
import com.lucienrowan.financemanager.db.retrieval.SqliteFinanceTypeRetrieval;
import com.lucienrowan.financemanager.gui.listcellrenderers.FinanceTypeListCellRenderer;
import com.lucienrowan.financemanager.objects.FinanceType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class FinanceTypeManager extends JFrame {
    private JPanel financeTypeMgrPanel;
    private JList financeTypeList;
    private JButton createButton;
    private JButton deleteButton;
    private JButton editButton;

    private LinkedList<FinanceType> financeTypes;

    public FinanceTypeManager() {
        setTitle("Fiancé - Finance Types");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(financeTypeMgrPanel);
        setMinimumSize(new Dimension(500, 500));
        retrieveFinanceTypes();
        setVisible(true);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createFinanceType();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FinanceType selectedFinanceType = financeTypes.get(financeTypeList.getSelectedIndex());

                    if (selectedFinanceType != null) {
                        editFinanceType(selectedFinanceType);
                    }

                    else {
                        JOptionPane.showMessageDialog(financeTypeMgrPanel, "Select a Finance Type");
                    }
                }

                catch (Exception ex) {
                    JOptionPane.showMessageDialog(financeTypeMgrPanel, "Select a Finance Type");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FinanceType selectedFinanceType = financeTypes.get(financeTypeList.getSelectedIndex());

                    if (selectedFinanceType != null) {
                        deleteFinanceType(selectedFinanceType);
                    }

                    else {
                        JOptionPane.showMessageDialog(financeTypeMgrPanel, "Select a Finance Type");
                    }
                }

                catch (Exception ex) {
                    JOptionPane.showMessageDialog(financeTypeMgrPanel, "Select a Finance Type");
                }
            }
        });
    }

    private void retrieveFinanceTypes() {
        financeTypes = SqliteFinanceTypeRetrieval.retrieveFinanceTypes();
        financeTypeList.setListData(financeTypes.toArray());

        FinanceTypeListCellRenderer financeTypeListCellRenderer =
                new FinanceTypeListCellRenderer("getName");

        financeTypeList.setCellRenderer(financeTypeListCellRenderer);
    }

    private void createFinanceType() {
        String financeTypeName = JOptionPane.showInputDialog("Please type in a name for the new finance type.");

        if (financeTypeName != null) {
            FinanceType financeType = new FinanceType(financeTypeName);
            SqliteInsert.insertFinanceType(financeType);
        }

        retrieveFinanceTypes();
    }

    private void editFinanceType(FinanceType selectedFinanceType) {
        String financeTypeName =
                JOptionPane.showInputDialog("Please edit the name for the finance type.",
                        selectedFinanceType.getName());

        if (financeTypeName != null) {
            selectedFinanceType.setName(financeTypeName);
            SqliteUpdate.updateFinanceType(selectedFinanceType);
        }

        retrieveFinanceTypes();
    }

    private void deleteFinanceType(FinanceType selectedFinanceType) {
        int decision = JOptionPane.showConfirmDialog(this,
                "Would you like to delete the selected finance type " + selectedFinanceType.getName() + "?");

        if (decision == JOptionPane.YES_OPTION) {
            SqliteDelete.deleteFinanceType(selectedFinanceType);

            JOptionPane.showMessageDialog(this,
                    "Finance Type " + selectedFinanceType.getName() + " has been deleted.");

            retrieveFinanceTypes();
        }
    }
}

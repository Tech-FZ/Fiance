package com.lucienrowan.financemanager.gui;

import com.lucienrowan.financemanager.db.SqliteInsert;
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
}

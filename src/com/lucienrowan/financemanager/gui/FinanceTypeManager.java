package com.lucienrowan.financemanager.gui;

import com.lucienrowan.financemanager.db.retrieval.SqliteFinanceTypeRetrieval;
import com.lucienrowan.financemanager.objects.FinanceType;

import javax.swing.*;
import java.awt.*;
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
    }

    private void retrieveFinanceTypes() {
        financeTypes = SqliteFinanceTypeRetrieval.retrieveFinanceTypes();
        financeTypeList.setListData(financeTypes.toArray());
    }
}

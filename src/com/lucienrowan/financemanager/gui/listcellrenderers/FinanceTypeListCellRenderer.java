package com.lucienrowan.financemanager.gui.listcellrenderers;

import com.lucienrowan.financemanager.objects.FinanceType;

import javax.swing.*;
import java.awt.*;

public class FinanceTypeListCellRenderer implements ListCellRenderer<FinanceType> {
    private final ListCellRenderer listCellRenderer;
    private String displayMember;

    public FinanceTypeListCellRenderer(String displayMember) {
        this(displayMember, new DefaultListCellRenderer());
    }

    public FinanceTypeListCellRenderer(String displayMember, ListCellRenderer wrapped) {
        listCellRenderer = wrapped;
        this.displayMember = displayMember;
    }

    private Object getDisplayMemberValue(Object value) {
        // value is the domain class
        // only works if display member is a method, TODO: fallback to field
        // displayMember is something like "getName" -> value.getName() gets called
        try {
            return value.getClass().getMethod(displayMember).invoke(value);
        } catch (Exception ex) {
            // if anything went wrong it is the programmers fault -> propagate exception
            throw new RuntimeException(ex);
        }
    }

    public String getDisplayMember() {
        return displayMember;
    }

    public void setDisplayMember(String displayMember) {
        this.displayMember = displayMember;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends FinanceType> list, FinanceType value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        Object displayMemberValue = getDisplayMemberValue(value);
        return listCellRenderer.getListCellRendererComponent(list, displayMemberValue, index, isSelected, cellHasFocus);
    }
}

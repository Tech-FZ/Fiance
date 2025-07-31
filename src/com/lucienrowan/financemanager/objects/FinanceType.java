package com.lucienrowan.financemanager.objects;

public class FinanceType {
    private int id;
    private String name;

    public FinanceType(String name) {
        this.name = name;
    }

    public FinanceType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
}

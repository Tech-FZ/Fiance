package com.lucienrowan.financemanager.objects;

public class FinanceType {
    private int id;
    private String name;

    public FinanceType(String name) {
        this.setName(name);
    }

    public FinanceType(int id, String name) {
        this.id = id;
        this.setName(name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.lucienrowan.financemanager.objects;

public class Income {
    private int id;
    private String description;
    private double amount;
    private int timeGroupId;

    public Income(String description, double amount, int timeGroupId) {
        this.description = description;
        this.timeGroupId = timeGroupId;
        setAmount(amount);
    }

    public Income(String description, double amount, int timeGroupId, int id) {
        this.description = description;
        this.timeGroupId = timeGroupId;
        setAmount(amount);
        this.id = id;
    }

    public void setAmount(double amount) {
        if (amount > 0) {
            this.amount = amount;
        }

        else {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
    }
}

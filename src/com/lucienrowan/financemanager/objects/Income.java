package com.lucienrowan.financemanager.objects;

public class Income {
    private int id;
    private String description;
    private double amount;
    private int timeGroupId;

    public Income(String description, double amount, int timeGroupId) {
        this.setDescription(description);
        this.setTimeGroupId(timeGroupId);
        setAmount(amount);
    }

    public Income(String description, double amount, int timeGroupId, int id) {
        this.setDescription(description);
        this.setTimeGroupId(timeGroupId);
        setAmount(amount);
        this.setId(id);
    }

    public void setAmount(double amount) {
        if (amount > 0) {
            this.amount = amount;
        }

        else {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeGroupId() {
        return timeGroupId;
    }

    public void setTimeGroupId(int timeGroupId) {
        this.timeGroupId = timeGroupId;
    }
}

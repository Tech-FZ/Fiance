package com.lucienrowan.financemanager.objects;

public class Spendings {
    private int id;
    private String description;
    private double amount;
    private int financeTypeId;
    private int timeGroupId;

    public Spendings(String description, double amount, int financeTypeId,
                     int timeGroupId) {
        this.description = description;
        setAmount(amount);
        this.financeTypeId = financeTypeId;
        this.timeGroupId = timeGroupId;
    }

    public Spendings(String description, double amount, int financeTypeId,
                     int timeGroupId, int id) {
        this.description = description;
        setAmount(amount);
        this.financeTypeId = financeTypeId;
        this.timeGroupId = timeGroupId;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        if (amount > 0) {
            this.amount = amount;
        }

        else {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
    }

    public void setFinanceTypeId(int financeTypeId) {
        this.financeTypeId = financeTypeId;
    }

    public void setFinanceTypeId(FinanceType financeType) {
        this.financeTypeId = financeType.getId();
    }

    public void setTimeGroupId(int timeGroupId) {
        this.timeGroupId = timeGroupId;
    }

    public void setTimeGroupId(TimeGroup timeGroup) {
        this.timeGroupId = timeGroup.getId();
    }
}

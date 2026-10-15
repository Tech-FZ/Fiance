package com.lucienrowan.financemanager.objects;

public class BudgetPercentages {
    private int id;
    private int timeGroupId;
    private int financeTypeId;
    private double percentage;
    private FinanceType financeType;

    public BudgetPercentages(int timeGroupId, int financeTypeId, double percentage) {
        this.setTimeGroupId(timeGroupId);
        this.financeTypeId = financeTypeId;
        this.setPercentage(percentage);
    }

    public BudgetPercentages(int timeGroupId, int financeTypeId, double percentage, int id) {
        this.setTimeGroupId(timeGroupId);
        this.financeTypeId = financeTypeId;
        this.setPercentage(percentage);
        this.setId(id);
    }

    public void setPercentage(double percentage) throws IllegalArgumentException {
        if (percentage > 0 && percentage <= 1) {
            this.percentage = percentage;
        }

        else {
            throw new IllegalArgumentException("Percentage must be between 0 and 1.");
        }
    }

    public FinanceType getFinanceType() {
        return financeType;
    }

    public void setFinanceType(FinanceType financeType) {
        this.financeType = financeType;
        this.financeTypeId = financeTypeId;
    }

    public double getPercentage() {
        return percentage;
    }

    public int getTimeGroupId() {
        return timeGroupId;
    }

    public void setTimeGroupId(int timeGroupId) {
        this.timeGroupId = timeGroupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

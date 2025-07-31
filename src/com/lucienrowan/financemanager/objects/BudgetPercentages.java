package com.lucienrowan.financemanager.objects;

public class BudgetPercentages {
    private int id;
    private int timeGroupId;
    private int financeTypeId;
    private double percentage;

    public BudgetPercentages(int timeGroupId, int financeTypeId, double percentage) {
        this.timeGroupId = timeGroupId;
        this.financeTypeId = financeTypeId;
        this.setPercentage(percentage);
    }

    public BudgetPercentages(int timeGroupId, int financeTypeId, double percentage, int id) {
        this.timeGroupId = timeGroupId;
        this.financeTypeId = financeTypeId;
        this.setPercentage(percentage);
        this.id = id;
    }

    public void setPercentage(double percentage) {
        if (percentage > 0 && percentage <= 1) {
            this.percentage = percentage;
        }

        else {
            throw new IllegalArgumentException("Percentage must be between 0 and 1.");
        }
    }
}

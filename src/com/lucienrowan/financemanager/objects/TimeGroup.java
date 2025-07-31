package com.lucienrowan.financemanager.objects;

public class TimeGroup {
    private int id;
    private int year;
    private int month;
    private int iteration;

    public TimeGroup(int year, int month) {
        this.year = year;
        setMonth(month);
    }

    public TimeGroup(int year, int month, int iteration) {
        this.year = year;
        setMonth(month);
        setIteration(iteration);
    }

    public TimeGroup(int id, int year, int month, int iteration) {
        this.id = id;
        this.year = year;
        setMonth(month);
        setIteration(iteration);
    }

    public void setMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.month = month;
        }

        else {
            throw new IllegalArgumentException("Invalid month value.");
        }
    }

    public void setIteration(int iteration) {
        if (iteration >= 0) {
            this.iteration = iteration;
        }

        else {
            throw new IllegalArgumentException("Iterations can only be 0 or greater.");
        }
    }

    public int getId() {
        return id;
    }
}

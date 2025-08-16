package com.lucienrowan.financemanager.objects;

public class TimeGroup {
    private int id;
    private int year;
    private Months month;
    private int iteration;

    public TimeGroup(int year, Months month) {
        this.year = year;
        setMonth(month);
    }

    public TimeGroup(int year, Months month, int iteration) {
        this.year = year;
        setMonth(month);
        setIteration(iteration);
    }

    public TimeGroup(int id, int year, Months month, int iteration) {
        this.id = id;
        this.year = year;
        setMonth(month);
        setIteration(iteration);
    }

    public void setMonth(Months month) {
        this.month = month;
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

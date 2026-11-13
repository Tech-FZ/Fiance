package com.lucienrowan.financemanager.events.args;

import com.lucienrowan.financemanager.events.publishers.BasePublisher;
import com.lucienrowan.financemanager.objects.DatabaseMode;
import com.lucienrowan.financemanager.objects.Income;
import jdk.jfr.Event;

public class IncomeDatabaseEvent extends BaseEvent {
    private Income income;
    private DatabaseMode databaseMode;

    public IncomeDatabaseEvent(BasePublisher publisher, Income income, DatabaseMode databaseMode) {
        super(publisher);
        this.income = income;
        this.databaseMode = databaseMode;
    }

    public Income getIncome() {
        return income;
    }

    public DatabaseMode getDatabaseMode() {
        return databaseMode;
    }
}

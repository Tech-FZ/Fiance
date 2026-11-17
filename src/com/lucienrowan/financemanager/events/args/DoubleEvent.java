package com.lucienrowan.financemanager.events.args;

import com.lucienrowan.financemanager.events.publishers.BasePublisher;

public class DoubleEvent extends BaseEvent {
    private double eventDouble;

    public DoubleEvent(BasePublisher publisher, double eventDouble) {
        super(publisher);
        this.eventDouble = eventDouble;
    }

    public double getEventDouble() {
        return eventDouble;
    }
}

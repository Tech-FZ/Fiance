package com.lucienrowan.financemanager.events.args;

import com.lucienrowan.financemanager.events.publishers.BasePublisher;
import jdk.jfr.Event;

public abstract class BaseEvent extends Event {
    private BasePublisher<BaseEvent> publisher;

    public BaseEvent(BasePublisher publisher) {
        this.publisher = publisher;
    }

    public BasePublisher<BaseEvent> getPublisher() {
        return publisher;
    }
}

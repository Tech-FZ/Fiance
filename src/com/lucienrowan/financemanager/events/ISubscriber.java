package com.lucienrowan.financemanager.events;

import jdk.jfr.Event;

public interface ISubscriber<T extends Event> {
    void doOperation(T e);
}

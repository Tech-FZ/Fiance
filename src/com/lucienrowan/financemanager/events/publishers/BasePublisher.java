package com.lucienrowan.financemanager.events.publishers;

import com.lucienrowan.financemanager.events.ISubscriber;
import jdk.jfr.Event;

import java.util.LinkedList;

public class BasePublisher<T extends Event> {
    private LinkedList<ISubscriber> subscribers;

    public BasePublisher() {
        subscribers = new LinkedList<>();
    }

    public void subscribe(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void publish(T e) {
        for (ISubscriber subscriber : subscribers) {
            subscriber.doOperation(e);
        }
    }
}

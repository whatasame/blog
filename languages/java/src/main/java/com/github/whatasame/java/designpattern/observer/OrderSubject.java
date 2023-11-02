package com.github.whatasame.java.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class OrderSubject {

    private final List<OrderObserver> observers;

    public OrderSubject() {
        this.observers = new ArrayList<>();
    }

    public void register(final OrderObserver observer) {
        this.observers.add(observer);
    }

    public void unregister(final OrderObserver observer) {
        this.observers.remove(observer);
    }

    public void notify(final String event) {
        for (OrderObserver observer : observers) {
            observer.update(event);
        }
    }
}

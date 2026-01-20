package com.java_inaction.newspaper_observer;

import java.util.ArrayList;
import java.util.List;

/**
 * unified place to inform every observer using notifyObservers()
 */
public class Feed implements Subject {

    private final List<Observer> observers = new ArrayList<Observer>();

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void notifyObservers(String tweet) {
        observers.forEach((o) -> o.notify(tweet));
    }

    /**
     * no need to implement every subclass one by one, using lambda which u
     * need to provide the single functional interface method implementation
     */
    public void registerObserversWLambda() {
        this.registerObserver((tweet) -> {
            if (tweet != null && tweet.contains("wine")) {
                System.out.println("Today cheese, and wine" + tweet);
            }
        });
    }
}

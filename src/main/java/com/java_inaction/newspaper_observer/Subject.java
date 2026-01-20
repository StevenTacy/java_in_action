package com.java_inaction.newspaper_observer;

public interface Subject {
    void registerObserver(Observer o);

    void notifyObservers(String tweet);
}

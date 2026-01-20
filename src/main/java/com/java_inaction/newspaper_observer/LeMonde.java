package com.java_inaction.newspaper_observer;

public class LeMonde implements Observer {

    public void notify(String tweet) {
        if (tweet != null && tweet.contains("wine")) {
            System.out.println("Today cheese, and wine" + tweet);
        }
    }
}

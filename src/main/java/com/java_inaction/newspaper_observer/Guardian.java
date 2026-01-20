package com.java_inaction.newspaper_observer;

public class Guardian implements Observer {

    public void notify(String tweet) {
        if (tweet != null && tweet.contains("queen")) {
            System.out.println("yet more news from queen" + tweet);
        }
    }
}

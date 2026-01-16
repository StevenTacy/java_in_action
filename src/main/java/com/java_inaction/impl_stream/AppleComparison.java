package com.java_inaction.impl_stream;

import java.util.Comparator;
import java.util.List;

public class AppleComparison implements Comparator<Apple> {

    public int compare(Apple a1, Apple a2) {
        return Integer.compare(a1.getWeight(), a2.getWeight());
    }

    public void compareAppleWInterface(List<Apple> appleList) {
        appleList.sort(new AppleComparison());
    }

    public void compareAppleWAnonymous(List<Apple> appleList) {
       appleList.sort(new Comparator<Apple>() {
           public int compare(Apple a1, Apple a2) {
               return Integer.compare(a1.getWeight(), a2.getWeight());
           }
       });
    }

    public void compareAppleWLambda(List<Apple> appleList) {
        appleList.sort((a1, a2) -> Integer.compare(a1.getWeight(), a2.getWeight()));
    }

    public void compareAppleWComparing(List<Apple> appleList) {
        appleList.sort(Comparator.comparing(Apple::getWeight));
    }

    public void compareAppleWComparingReverse(List<Apple> appleList) {
        appleList.sort(Comparator.comparing(Apple::getWeight).reversed());
    }

    /**
     * using comparing to initiate comparator and then sorted reverse by weight then country
     */
    public void compareAppleWComparingReverseThenComparing(List<Apple> appleList) {
        appleList.sort(Comparator
                .comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getCountry)
        );
    }
}

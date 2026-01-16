package com.java_inaction.impl_stream;

import java.util.function.Predicate;

public class ApplePredicator {

    public void appleIsRedAndHeavy() {
        Predicate<Apple> greenApple = a -> a.getColor().equals(Apple.Color.GREEN);
        Predicate<Apple> heavy = a -> a.getWeight() > 150;
        Predicate<Apple> notGreenButHeavyApple = greenApple.negate().and(heavy);
        Predicate<Apple> redAppleButLight = heavy.negate().and(a -> a.getColor().equals(Apple.Color.RED));
    }
}

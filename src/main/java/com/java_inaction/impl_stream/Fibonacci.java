package com.java_inaction.impl_stream;

import java.util.stream.Stream;

public class Fibonacci {

    public void printFirst20FibonacciPairs() {
        Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]})
                .limit(20)
                .forEach(f -> System.out.println(f[0] + ", " + f[1]));
    }

    /**
     * iterate can take in predicate to tell the stream when to stop within iteration
     */
    public void iteratePredicate() {
        Stream.iterate(0, n -> n < 100, n -> n + 4)
                .forEach(System.out::println);
    }
}

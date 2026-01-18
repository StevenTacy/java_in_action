package com.java_inaction.impl_stream;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GenerateStream {
    public void generateRandom5Int() {
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    public void printFirst20FibonacciPairs() {
        IntSupplier fib = this.fibonacci();
        IntStream.generate(fib).limit(20).forEach(System.out::println);
    }

    /**
     * instantiate a state of IntSupplier which will store the previous and current fibonacci pair
     * doing this cuz generate is stateless, which means it won't like iterate depends on previous
     * iterated return value
     */
    private IntSupplier fibonacci() {
        IntSupplier fibonacci = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.current + this.previous;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };

        return fibonacci;
    }
}

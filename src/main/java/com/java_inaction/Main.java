package com.java_inaction;

import com.java_inaction.collector.PrimeNumbersCollector;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final long N = 10_000_000L;

    static void main() {
        getDuration((range) -> {
            Stream.iterate(0L, i -> i + 1)
                    .limit(range)
                    .reduce(0L, Long::sum);
        }, N);

        getDuration((range) -> {
            LongStream.rangeClosed(0L, range)
                    .reduce(0L, Long::sum);
        }, N);

        getDuration((range) -> {
            Stream.iterate(0L, i -> i + 1)
                    .limit(range)
                    .parallel()
                    .reduce(0L, Long::sum);
        }, N);

        getDuration((range) -> {
            LongStream.iterate(0L, i -> i + 1)
                    .limit(range)
                    .parallel()
                    .reduce(0L, Long::sum);
        }, N);

        getDuration((range) -> {
            LongStream.rangeClosed(0L, range)
                    .parallel()
                    .reduce(0L, Long::sum);
        }, N);

        getDuration((range) -> {
            long i = 0L;
            while (i <= range) {
                i++;
            }
        }, N);

        getDuration(N);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int range) {
        return IntStream.rangeClosed(2, range).boxed().collect(new PrimeNumbersCollector());
    }

    public static void getDuration(Consumer<Long> consumer, long range) {

        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            consumer.accept(range);
            long duration = System.nanoTime() - start;
            if (duration < fastest) fastest = duration;
        }
        System.out.println(fastest);
    }

    public static void getDuration(long range) {

        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long res = 0L;
            for (long j = 0L; j <= range; j++) {
                res += j;
            }
            long duration = System.nanoTime() - start;
            if (duration < fastest) fastest = duration;
        }
        System.out.println(fastest);
    }
}

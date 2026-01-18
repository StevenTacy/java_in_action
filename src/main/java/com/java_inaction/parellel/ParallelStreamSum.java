package com.java_inaction.parellel;


import java.util.function.Consumer;
import java.util.stream.LongStream;
import java.util.stream.Stream;

//@BenchmarkMode(Mode.AverageTime)
//@OutputTimeUnit(TimeUnit.MILLISECONDS)
//@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xms4G"})
public class ParallelStreamSum {

    private static final long N = 10_000_000L;

    //    @Benchmark
    public static long getSum() {
        return Stream.iterate(1L, i -> i + 1)
                .limit(N)
                .reduce(0L, Long::sum);
    }

    //    @TearDown(Level.Invocation)
    public void tearDown() {
        System.gc();
    }

    public static long getSumWParallel() {
        return Stream.iterate(0L, i -> i + 1)
                .limit(N)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long getSumWWhileLoop() {
        long i = 1L;
        while (i <= N) {
            i++;
        }
        return i;
    }

    public static long getSumWForLoop() {
        long res = 0L;
        for (long i = 1L; i <= N; i++) {
            res += i;
        }
        return res;
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

    /**
     * mutating shared state of accumulator -> cause issue
     */
    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static void main(String[] args) {
//        System.out.println("Side effect sum: " + sideEffectSum(N));
        System.out.println("Side effect sum: " + sideEffectParallelSum(N));
    }
}

package com.java_inaction.parellel;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

@Data
@RequiredArgsConstructor
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public static long forkJoinSum(long num) {
        long[] numbers = LongStream.rangeClosed(0, num).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static void main(String[] args) {
        Instant start = Instant.now();
        long sum = forkJoinSum(10_000_000);
        System.out.println("Duration: " + Duration.between(start, Instant.now()).toMillis());

        start = Instant.now();
        long sum2 = LongStream.rangeClosed(0, 10_000_000).parallel().sum();
        System.out.println("Duration: " + Duration.between(start, Instant.now()).toMillis());

        start = Instant.now();
        long sum3 = LongStream.rangeClosed(0, 10_000_000).parallel().reduce(0L, Long::sum);
        System.out.println("Duration: " + Duration.between(start, Instant.now()).toMillis());
    }

    protected Long compute() {
        int length = end - start;
        if (length < THRESHOLD) return computeSequentially();
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        long rightResult = rightTask.compute();
        long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0L;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}

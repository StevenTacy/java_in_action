package com.java_inaction.collector;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

public class PartitionIsPrimeNumber {

    private boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    /**
     * partitioningBy Predicate helper function
     */
    public Map<Boolean, List<Integer>> getPrimeNumberByInput(int range) {
        return IntStream.rangeClosed(2, range).boxed().collect(partitioningBy(this::isPrime));
    }
}

package com.java_inaction.collector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

public class PartitionIsPrimeNumber {

    private static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    /**
     * partitioningBy Predicate helper function
     */
    public static Map<Boolean, List<Integer>> getPrimeNumberByInput(int range) {
        return IntStream.rangeClosed(2, range).boxed().collect(partitioningBy(PartitionIsPrimeNumber::isPrime));
    }

    public static void getPrimeNumberByCustomerizeCollector(int range) {
        var primeNumberMapWCustomerizeCollector = IntStream.rangeClosed(2, range).boxed()
                .collect(
                        () -> new HashMap<Boolean, List<Integer>>() {{
                            put(true, new ArrayList<>());
                            put(false, new ArrayList<>());
                        }},
                        (map, candidate) -> {
                            map.get(PrimeNumbersCollector.isPrime(map.get(true), candidate)).add(candidate);
                        },
                        HashMap::putAll
                );
    }
}

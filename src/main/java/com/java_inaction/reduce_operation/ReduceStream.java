package com.java_inaction.reduce_operation;

import java.util.Arrays;
import java.util.OptionalInt;

public class ReduceStream {

    public void getSumByArray(int[] nums) {
        int sum = Arrays.stream(nums).reduce(0, (a, b) -> a + b);
        int sumWFunctionalInterface = Arrays.stream(nums).reduce(0, Integer::sum);
        OptionalInt noInitialVal = Arrays.stream(nums).reduce(Integer::sum);
    }
}

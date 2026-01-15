package com.java_inaction.functional_interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionImpl {
    public <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t: list) {
            result.add(f.apply(t));
        }
        return result;
    }

    public void mapStringLengthToInt() {
        var intList = map(Arrays.asList("macle", "diamond", "silver", "bronze"), String::length);
    }
}

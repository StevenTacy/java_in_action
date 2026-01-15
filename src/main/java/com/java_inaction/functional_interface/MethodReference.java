package com.java_inaction.functional_interface;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class MethodReference {

    public void stringToInt() {
        ToIntFunction<String> stringToInt = s -> Integer.parseInt(s);
        ToIntFunction<String> stringToIntMR = Integer::parseInt;
    }

    public void ifListContainsString() {
        BiPredicate<List<String>, String> contains = (list, s) -> list.contains(s);
        BiPredicate<List<String>, String> containsMR = List::contains;
    }

    public void checkStartWithNumber() {
        Predicate<String> predicate = s -> this.startsWithNumber(s);
        Predicate<String> predicateMR = this::startsWithNumber;
    }

    public boolean startsWithNumber(String s) {
        return Character.isDigit(s.charAt(0));
    }
}

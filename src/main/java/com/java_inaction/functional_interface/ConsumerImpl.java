package com.java_inaction.functional_interface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerImpl {
    public <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t: list) {
            c.accept(t);
        }
    }

    public void printListInteger() {
        forEach(Arrays.asList(1, 2, 3, 4, 5), i -> System.out.println(i));
        forEach(Arrays.asList(1, 2, 3, 4, 5), System.out::println);
    }
}

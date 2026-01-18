package com.java_inaction.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    /**
     * provide a accumulator for transform stream into target collection
     *
     * @return new ArrayList<>()
     */
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    /**
     * return a consumer which will accept the accumulator function to transform the stream into result
     */
    public BiConsumer<List<T>, T> accumulator() {
//        return (list, item) -> list.add(item);
        return List::add;
    }

    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    /**
     * using parallel need this step
     * which will combine multi-accumulators into a single one and return it
     */
    public BinaryOperator<List<T>> combiner() {
        return (a, b) -> {
            a.addAll(b);
            return a;
        };
    }

    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED, Characteristics.CONCURRENT));
    }
}

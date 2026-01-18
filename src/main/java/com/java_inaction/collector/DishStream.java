package com.java_inaction.collector;

import com.java_inaction.impl_stream.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DishStream {

    /**
     * implementing own collector need to instantiate it within the collect method
     */
    public void transformDishStreamToList(Stream<Dish> dishStream) {
        var dishList = dishStream.collect(new ToListCollector());
    }

    /**
     * using overload of collect function to get the same result,
     * u need to manually provide Supplier, Biconsumer, BinaryOperator
     */
    public void transformDishStreamToListWOverLoad(Stream<Dish> dishStream) {
        var dishList = dishStream.collect(
                ArrayList::new,
                List::add,
                List::addAll
        );
    }
}

package com.java_inaction.impl_stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
public class Dish {
    public enum Type {
        FISH, MEAT, OTHERS
    }

    private String name;
    private boolean isVegetarian;
    private int calories;
    private Type type;

//    List<Dish> specialMenu = Arrays.asList(
//            new Dish("seasonal fruit", true, 120, Dish.Type.OTHERS),
//            new Dish("prawns", false, 300, Dish.Type.FISH),
//            new Dish("rice", true, 350, Dish.Type.OTHERS),
//            new Dish("chicken", false, 400, Dish.Type.MEAT),
//            new Dish("french fries", true, 530, Dish.Type.OTHERS));

    /**
     * take while will stop when filter condition no longer true
     */
    public List<Dish> getDishesByTakeWhileFilter(List<Dish> dishes, Predicate<Dish> filter) {
        return dishes.stream()
                .takeWhile(filter)
                .toList();
    }

    /**
     * drop while will drop everything filtered by filter and stop when filter condition no longer true
     */
    public List<Dish> getDishesByDropWhileFilter(List<Dish> dishes, Predicate<Dish> filter) {
        return dishes.stream()
                .dropWhile(filter)
                .toList();
    }

    public List<Dish> getFirstTwoDishesOfMeat(List<Dish> dishes) {
        return dishes.stream()
                .filter(d -> d.getType().equals(Type.MEAT))
                .limit(2)
                .toList();
    }

    /**
     * using two map to extract the length of every dish's name
     * first map to extract, second map to extract the length of each name
     */
    public List<Integer> getDishesLengthByMap(List<Dish> dishes) {
        return dishes.stream()
                .map(Dish::getName)
                .map(String::length)
                .toList();
    }

    public boolean anyDishVegetarian(List<Dish> dishes) {
        return dishes.stream()
                .anyMatch(Dish::isVegetarian);
    }

    public boolean findAnyVegetarian(List<Dish> dishes) {
        Optional<Dish> dish = dishes.stream().filter(Dish::isVegetarian).findAny();
        return dishes.stream().filter(Dish::isVegetarian).findAny().isPresent();
    }

    /**
     * using stream to convert T -> int using mapToInt
     * using map to map T -> int need to boxed the primitive int to Integer which is overhead
     */
    public int sumAllDishCalories(List<Dish> dishes) {
        return dishes.stream()
                .mapToInt(Dish::getCalories)
                .sum();
    }

    public void intStreamToStream(List<Dish> dishes) {
        IntStream integerStream = dishes.stream().mapToInt(Dish::getCalories);
        Stream<Integer> intToStream = integerStream.boxed();
    }

    public int optionalMaxCalories(List<Dish> dishes) {
        return dishes.stream().mapToInt(Dish::getCalories).max().orElseGet(() -> 1);
    }

    public void pythagoreanTriples() {
        Stream<double[]> triples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0));
    }
}

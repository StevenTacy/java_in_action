package com.java_inaction.impl_stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
public class Dish {

    public enum Type {
        FISH, MEAT, OTHERS
    }

    public enum CaloriesLevel {
        FAT, NORMAL, DIET
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

    public static Map<Type, List<Dish>> getDishByType(List<Dish> dishes) {
        var dishesGroupingByFiltering = dishes.stream().collect(groupingBy(Dish::getType, filtering(
                d -> d.getCalories() < 500, toList())));

        var dishesGroupingNameByMapping = dishes.stream().collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
        return dishes.stream().collect(groupingBy(Dish::getType, filtering(
                d -> d.getCalories() < 500, toList()
        )));
    }

    public static Map<Type, Set<String>> getDishesNameByType(List<Dish> dishes) {
        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));

        return dishes.stream().collect(groupingBy(Dish::getType, flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));
    }

    public static void multiGroupingBy(List<Dish> dishes) {
        var dishesGroupByTypeGroupByCaloriesLevel = dishes.stream().collect(groupingBy(
                Dish::getType, groupingBy(
                        dish -> {
                            if (dish.getCalories() < 400) return CaloriesLevel.DIET;
                            else if (dish.getCalories() < 700) return CaloriesLevel.NORMAL;
                            else return CaloriesLevel.FAT;
                        }
                )));
    }

    public static void groupingByMaxCalories(List<Dish> dishes) {
        // note this optional will not create the mapping cuz it might be null and due to stream is lazily forming pipeline
        // it only initialize the key when it first met the corresponding value in this case (max result)
        // therefor, if maxby has nothing to deal with it won't instantiated the optional object
        var maxByCalories = dishes.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
        // using collectiongAndThen -> collecting the result and operate a function.map()
        var collectingAndThenMaxByCalories = dishes.stream().collect(
                groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
    }

    public static void mappingCaloriesByTypeByHashSet(List<Dish> dishes) {
        var mappingCaloriesByTypeByHashSet = dishes.stream().collect(groupingBy(Dish::getType,
                mapping(
                        dish -> {
                            if (dish.getCalories() < 400) return CaloriesLevel.DIET;
                            else if (dish.getCalories() < 700) return CaloriesLevel.NORMAL;
                            else return CaloriesLevel.FAT;
                        }, toCollection(HashSet::new))));
    }

    public static void partitionByVegetarian(List<Dish> dishes) {
        var veganAndNonVeganMap = dishes.stream().collect(partitioningBy(Dish::isVegetarian));
        var veganAndNonVeganMapGroupByType = dishes.stream().collect(partitioningBy(Dish::isVegetarian,
                groupingBy(Dish::getType)));
        var veganAndNonVeganMaxCaloriesDish = dishes.stream().collect(partitioningBy(Dish::isVegetarian,
                collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
    }
}

package com.java_inaction.collector;

import com.java_inaction.impl_stream.Dish;

import java.util.*;
import java.util.stream.Collectors;

public class DishCollector {

    public void getNumberOfDishes(List<Dish> dishes) {
        long count = dishes.stream().count();
    }

    public void getMaxCaloriesDish(List<Dish> dishes) {
        Optional<Dish> maxCaloriesDish = dishes.stream()
                .max(Comparator.comparing(Dish::getCalories));
    }

    public void summingAllDishesCalories(List<Dish> dishes) {
        int total = dishes.stream().collect(Collectors.summingInt(Dish::getCalories));
        int totalWMap = dishes.stream().mapToInt(Dish::getCalories).sum();
    }

    public void getAllDishesSummary(List<Dish> dishes) {
        IntSummaryStatistics allCalories = dishes.stream().collect(Collectors.summarizingInt(Dish::getCalories));
    }

    /**
     * joining overload function with delimiters and no delimiters
     */
    public void getMenuNameString(List<Dish> dishes) {
        String allNamesWithNoDelimiters = dishes.stream().map(Dish::getName).collect(Collectors.joining());
        String namesWithDelimiters = dishes.stream().map(Dish::getName).collect(Collectors.joining(", "));
    }

    public int getCaloriesWReducing(List<Dish> dishes) {
        int sum = dishes.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        Optional<Dish> mostCaloriesDish = dishes.stream().collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        return sum;
    }

    public Map<Dish.Type, List<Dish>> getDishesGroupByType(List<Dish> dishes) {
        return dishes.stream().collect(Collectors.groupingBy(Dish::getType));
    }

    public Map<Dish.CaloriesLevel, List<Dish>> getDishesGroupByCaloriesLevel(List<Dish> dishes) {
        return dishes.stream().collect(Collectors.groupingBy(d -> {
            if (d.getCalories() > 700) return Dish.CaloriesLevel.FAT;
            else if (d.getCalories() < 700 && d.getCalories() >= 400) return Dish.CaloriesLevel.NORMAL;
            else return Dish.CaloriesLevel.DIET;
        }));
    }
}

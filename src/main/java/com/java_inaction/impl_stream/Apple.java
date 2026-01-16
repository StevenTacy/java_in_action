package com.java_inaction.impl_stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.java_inaction.impl_stream.Apple.Color.GREEN;
import static java.util.stream.Collectors.toList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Apple {

    enum Color {
        GREEN, RED
    }

    private Color color;
    private int weight;
    private String country;

    /**
     * constructor using for Function<Integer, Apple>
     */
    public Apple(int weight) {
        this.weight = weight;
    }

    /**
     * using ApplePredicate which will force every criterion to instantiate object and implements the ApplePredicate interface
     */
    static List<Apple> forLoopGetAllApples(List<Apple> inventory, ApplePredicate p) {
        var appleList = new ArrayList<Apple>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                appleList.add(apple);
            }
        }
        return appleList;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        var result = new ArrayList<T>();
        for (T item : list) {
            if (p.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static List<Apple> getHeavyApples(List<Apple> inventory) {
        return inventory.stream().filter(a -> a.getWeight() > 150).collect(toList());
    }

    /**
     * using an empty constructor which makes Supplier has functionality to create an empty Apple Object
     */
    public void initiatedAppleWLambda() {
        Supplier<Apple> sa = Apple::new;
        Apple a1 = sa.get();
    }

    public void initiatedAppleWFunctionLambda(int weight) {
        Function<Integer, Apple> functionApple = Apple::new;
        functionApple.apply(weight);
        Function<Integer, Apple> functionAppleExplicit = (i) -> new Apple(i);
    }

    public List<Apple> map(List<Integer> list, Function<Integer, Apple> functionApple) {
        var result = new ArrayList<Apple>();
        for (Integer item : list) {
            result.add(functionApple.apply(item));
        }
        return result;
    }

    public void mapIntegerToApple() {
        var intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        var appleList = map(intList, Apple::new);
    }

    /**
     * anonymous class to parameterized input apple criteria
     */
    public List<Apple> getAppleByPredicate(List<Apple> inventory) {
        var appList = forLoopGetAllApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple a) {
                return GREEN.equals(a.getColor());
            }
        });

        return appList;
    }

    public void lambdaExpressionWithGeneric() {
        var appList = new ArrayList<Apple>();
        var lambdaFilteredGreenApples = filter(appList, (Apple a) -> a.getColor().equals(GREEN));
        var lambdaFilteredHeavyApples = filter(appList, (Apple a) -> a.getWeight() > 150);
        var intList = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).toList();
        var intBiggerThanFive = filter(intList, (Integer i) -> i > 5);
    }

    public void sortApplesByLambdaExpression(List<Apple> inventory) {
        inventory.sort(Comparator.comparingInt(Apple::getWeight));
    }

    /**
     * key point of using lambda for interface is that the subsequent following by arrow is the implementation of that interface
     */
    public Thread getThreadByLambda() {
        return new Thread(() -> System.out.println("creating a new thread"));
    }

    public void executorWithLambdaAndAnonymousExpr() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> threadName = executor.submit(new Callable<String>() {
            public String call() {
                return Thread.currentThread().getName();
            }
        });

        Future<String> lambdaThreadName = executor.submit(() -> Thread.currentThread().getName());
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        return inventory.stream().filter(a -> a.getColor().equals(color)).collect(toList());
    }

    static List<Apple> getGreenApples(List<Apple> inventory) {
        return inventory.stream().filter(a -> GREEN.equals(a.getColor())).collect(toList());
    }

    public static boolean isGreenApple(Apple apple) {
        return GREEN.equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public interface Predicate<T> {
        boolean test(T t);
    }

    public void getApplList() {

        var appList = new ArrayList<Apple>();
        Apple.builder().color(GREEN).weight(100).build();
        appList.add(Apple.builder().color(Apple.Color.RED).weight(100).build());
        appList.add(Apple.builder().color(GREEN).weight(120).build());
        appList.add(Apple.builder().color(GREEN).weight(200).build());
        appList.add(Apple.builder().color(GREEN).weight(10).build());

        forLoopGetAllApples(appList, Apple::isGreenApple);
        forLoopGetAllApples(appList, (Apple a) -> a.getWeight() > 150);
        forLoopGetAllApples(appList, (Apple a) -> a.getColor().equals(GREEN));
    }
}

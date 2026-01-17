package com.java_inaction.mapvsflatmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamsToStream {

    /**
     * in this example using two maps first convert List<String>  to String[]
     * then make individual array element to become a stream
     * which make toList return List<Stream<String>> not valid what we want is List<String>
     * using flatmap make several streams to amalgamated into single stream
     */
    public static List<String> splitWordWithMap(List<String> words) {
//        var streamList = words.stream()
//                .map(s -> s.split(""))
//                .map(Arrays::stream)
//                .toList();

        return words.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .toList();
    }

    public static List<Integer> getSquareIntList(List<Integer> intList) {
        return intList.stream()
                .map(i -> i * i)
                .toList();
    }

    public static List<int[]> getPairsByTwoLists(List<Integer> list1, List<Integer> list2) {
        List<int[]> pairs = list1.stream()
                .flatMap(i -> list2.stream()
                        .map(j -> new int[]{i, j}))
                .toList();
        return pairs;
    }
}

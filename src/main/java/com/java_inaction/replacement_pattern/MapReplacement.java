package com.java_inaction.replacement_pattern;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Map.entry;

public class MapReplacement {

    public static void replaceAllWMap() {

//        var replacedMap = Map.ofEntries(entry("Gin", "Peaky blinders"), entry("Amberly", "Fast and furious"));
        var replacedMap = new HashMap<String, String>();
        replacedMap.put("Gin", "Peaky blinders");
        replacedMap.put("Amberly", "Fast and furious");
        replacedMap.replaceAll((ppl, movie) -> movie.toUpperCase());
    }

    public static void mergeConflictMap() {
        Map<String, String> family = Map.ofEntries(
                entry("Teo", "Star Wars"), entry("Cristina", "James Bond"));
        Map<String, String> friends = Map.ofEntries(
                entry("Raphael", "Star Wars"), entry("Cristina", "Matrix"));

        Map<String, String> everyone = new HashMap<>(family);
        friends.forEach((k, v) -> everyone.merge(k, v, (m1, m2) -> m1 + "&" + m2));
        System.out.println(everyone);
    }

    public static void mergeInitializeCheck() {
        Map<String, Long> family = new HashMap<>();
        final String name = "George";
        family.merge(name, 1L, (key, count) -> count + 1L);
    }

    public static void concurrentHashMap() {
        var concurrentHashMap = new ConcurrentHashMap<String, Long>();
        // declare threshold for limit the min size of parallelism
        long threshold = 1;
        Optional<Long> maxVal = Optional.ofNullable(concurrentHashMap.reduceValues(threshold, Long::max));
        var keySet = concurrentHashMap.keySet();
    }

    public static void main(String[] args) {
        mergeConflictMap();
    }
}

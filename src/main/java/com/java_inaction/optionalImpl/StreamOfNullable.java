package com.java_inaction.optionalImpl;

import java.util.stream.Stream;

public class StreamOfNullable {

    public void nullableStringStream() {
        Stream<String> stream = Stream.ofNullable(System.getProperty("home"));
        Stream<String> streamWFlatMap = Stream.of("home", "user", "admin")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)));
    }
}

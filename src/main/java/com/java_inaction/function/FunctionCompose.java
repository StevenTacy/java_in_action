package com.java_inaction.function;

import java.util.function.Function;

public class FunctionCompose {

    public void getIntactLetter() {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> intactLetter = addHeader
                .andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
    }
}

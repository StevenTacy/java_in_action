package com.java_inaction.impl_stream;

import java.util.function.Function;

public class AppleFunctionCompose {

    /*
     * utilize multi Function and composing then with andThen()
     */
    public void composingFunctionWInteger() {
        Function<Integer, Integer> plusF = i -> i++;
        Function<Integer, Integer> multiplyF = i -> i * i;
        Function<Integer, Integer> andThenF = plusF.andThen(multiplyF);
        int result = andThenF.apply(100);
    }

    public void composingFunctionWCompose() {
        Function<Integer, Integer> plusF = i -> i++;
        Function<Integer, Integer> multiplyF = i -> i * i;
        Function<Integer, Integer> andThenF = plusF.compose(multiplyF);
        int result = andThenF.apply(100);
    }
}

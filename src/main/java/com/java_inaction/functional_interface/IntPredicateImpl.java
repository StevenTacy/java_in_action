package com.java_inaction.functional_interface;

import com.java_inaction.impl_stream.Apple;

import java.util.function.Predicate;

public class IntPredicateImpl {

    /**
     * use a customerized int Predicate can put primitive type into parameterized args
     * whereas using Predicate<T> can only put in Reference type in this case Integer
     */
    public void checkEvenNumber() {
        IntPredicate ip = i -> i % 2 == 0;
        ip.test(3);
        Predicate<Integer> predicateInt = i -> i % 2 == 0;
        predicateInt.test(3);
    }
}

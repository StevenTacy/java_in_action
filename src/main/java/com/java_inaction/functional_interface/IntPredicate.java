package com.java_inaction.functional_interface;

/**
 * use a customerized int Predicate can put primitive type into parameterized args
 * whereas using Predicate<T> can only put in Reference type in this case Integer
 */
public interface IntPredicate {
    public boolean test(int i);
}

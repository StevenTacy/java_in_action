package com.java_inaction.functional_interface;

public class LocalVariableAssignment {

    /**
     * using local variable in lambda should only cast once which should make it a final
     * just something like -> this.
     */
    public void assignLocalVariableToLambda() {
        int luckyNumber = 777;
        Runnable r = () -> System.out.println(luckyNumber);
//      bad reassignment of local variable ->  luckyNumber = 444;
    }
}

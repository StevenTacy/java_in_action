package com.junit_in_action.cauculator;

import lombok.Data;

@Data
public class Calculator {

    public double add(double i1, double i2) {
        return i1 + i2;
    }

    public double subtract(double i1, double i2) {
        return i1 - i2;
    }

    public double multiply(double i1, double i2) {
        return i1 * i2;
    }

    public double divide(double i1, double i2) {
        return i1 / i2;
    }
}

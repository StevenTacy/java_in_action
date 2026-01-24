package com.junit_in_action.calculator;

import com.junit_in_action.cauculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    /**
     * @Test make this test into unit test
     */
    @Test
    public void testAdd() {
        var cal = new Calculator();
        var res = cal.add(10, 50);
        assertEquals(60, res);
    }
}

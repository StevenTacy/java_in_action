package com.junit_in_action.nested_classes;

import com.junit_in_action.customer.Customer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * using @Tag to divide groups with testing
 */
@Tag("individual")
public class CustomerTest {
    private static final String name = "Steven Stacey";

    @Test
    void customerFullNameTest() {
        var customer = Customer.builder()
                .fullName("Steven Stacey")
                .build();
        assertEquals(name, customer.getFullName());
    }
}

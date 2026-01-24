package com.junit_in_action.nested_classes;

import com.junit_in_action.customer.CustomerRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("reposiotry")
public class CustomerRepositoryTest {
    private static final String NAME = "Stevem Stacey";
    private CustomerRepository custRepo = new CustomerRepository();

    @Test
    void testNotExists() {
    }

    @Test
    void testCustomerPersistence() {
    }
}

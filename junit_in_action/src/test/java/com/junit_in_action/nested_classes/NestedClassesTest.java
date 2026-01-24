package com.junit_in_action.nested_classes;

import com.junit_in_action.customer.Customer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NestedClassesTest {
    private static final String FIRST_NAME = "Steven";
    private static final String LAST_NAME = "Stacey";

    @Nested
    class BuilderTest {
        private final String middleName = "Micael";

        @Disabled
        @Test
        void customerBuilder() throws ParseException {
            var dtFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            var dt = LocalDate.parse("2020/12/23", dtFormatter);
            var customer = Customer.builder()
                    .gender(Customer.Gender.MALE)
                    .firstName(FIRST_NAME)
                    .middleName(this.middleName)
                    .lastName(LAST_NAME)
                    .becameCustomerDate(dt)
                    .build();
            assertAll(() -> {
                assertEquals(Customer.Gender.MALE, customer.getGender());
                assertEquals(FIRST_NAME, customer.getFirstName());
                assertEquals(LAST_NAME, customer.getLastName());
                assertEquals(middleName, customer.getMiddleName());
                assertEquals(dt, customer.getBecameCustomerDate());
            });
        }

        @Tag("individual")
        @Test
        void customerFullNameTest() {
            var customerWName = Customer.builder()
                    .firstName(FIRST_NAME)
                    .middleName(middleName)
                    .lastName(LAST_NAME)
                    .build();
            String fullName = FIRST_NAME + " " + middleName + " " + LAST_NAME;
            assertEquals(fullName, customerWName.getFullCustomerName());
        }
    }
}


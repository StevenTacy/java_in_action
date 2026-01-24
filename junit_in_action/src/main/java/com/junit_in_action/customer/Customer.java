package com.junit_in_action.customer;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Customer {
    public enum Gender {
        MALE, FEMALE
    }

    private Gender gender;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private LocalDate becameCustomerDate;

    public String getFullCustomerName() {
        return String.format("%s %s %s", this.firstName, this.middleName, this.lastName);
    }
}

package com.java_inaction.optionalImpl;

import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class Person {
    private Optional<Car> car;
    private int age;

    public Set<String> getCarInsuranceName(List<Person> persons) {
        var optionalStream = persons.stream()
                .map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optIns -> optIns.map(Insurance::getName));

        return optionalStream.filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
}

package com.java_inaction.optionalImpl;

import lombok.Data;

import java.util.Optional;

@Data
public class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> nullSafeGetInsurance(Optional<Person> optionalPerson, Optional<Car> optionalCar) {
        return optionalPerson.flatMap(p -> optionalCar.map(c -> getCheapestInsurance(p, c)));
    }

    public void filterOptionalInsurance(Optional<Insurance> optionalInsurance) {
        optionalInsurance.filter(i -> "CambridgeInsurance".equals(i.getName()))
                .ifPresent(System.out::println);
    }

    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() > minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    private Insurance getCheapestInsurance(Person p, Car c) {
        return new Insurance();
    }
}

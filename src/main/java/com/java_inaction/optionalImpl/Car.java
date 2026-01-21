package com.java_inaction.optionalImpl;

import lombok.Data;

import java.util.Optional;

@Data
public class Car {
    private Optional<Insurance> insurance;
}

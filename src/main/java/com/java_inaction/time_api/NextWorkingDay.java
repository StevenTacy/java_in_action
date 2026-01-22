package com.java_inaction.time_api;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.*;

public class NextWorkingDay implements TemporalAdjuster {
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        var addDay = 1;
        if (dow == DayOfWeek.FRIDAY) {
            addDay = 3;
        } else if (dow == DayOfWeek.SATURDAY) {
            addDay = 2;
        }
        return temporal.plus(addDay, ChronoUnit.DAYS);
    }

    /**
     * implementing TemporalAdjuster to self-made a LocalDate adjustment function
     */
    public static TemporalAdjuster nextWorkingDay() {
        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
                t -> {
                    DayOfWeek dow = DayOfWeek.of(t.get(ChronoField.DAY_OF_WEEK));
                    var add = 1;
                    if (dow == DayOfWeek.FRIDAY) {
                        add = 3;
                    } else if (dow == DayOfWeek.SATURDAY) {
                        add = 2;
                    }
                    return t.plus(add, ChronoUnit.DAYS);
                }
        );
        LocalDate localDate = LocalDate.now();
        return localDate.with(nextWorkingDay);
    }
}

package com.java_inaction.time_api;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

/**
 * LocalDate, Instant, Period, Duration related is immutable
 * using with or get to manipulate time object
 */
public class TemporalField {

    public void localDate() {
        LocalDate faked = LocalDate.of(2017, 9, 23);
        int year = faked.get(ChronoField.YEAR);
        int month = faked.get(ChronoField.MONTH_OF_YEAR);
        int day = faked.get(ChronoField.DAY_OF_MONTH);
        int year1 = faked.getYear();
        int month1 = faked.getMonthValue();
        int day1 = faked.getDayOfMonth();
    }

    public void localTime() {
        LocalTime faked = LocalTime.of(12, 59, 23);
        int hour = faked.get(ChronoField.HOUR_OF_DAY);
        int minute = faked.get(ChronoField.MINUTE_OF_HOUR);
        int second = faked.get(ChronoField.SECOND_OF_MINUTE);
        int hour1 = faked.getHour();
        int minute1 = faked.getMinute();
        int second1 = faked.getSecond();
    }

    public void parseLocalDataAndTime() {
        LocalDate fakedDate = LocalDate.parse("2014-12-02");
        LocalTime fakedTime = LocalTime.parse("13:23:55");
    }

    public void localDateTime() {
        LocalDate fakedDate = LocalDate.parse("2014-12-02");
        LocalTime fakedTime = LocalTime.parse("13:23:55");
        LocalDateTime dt1 = LocalDateTime.of(fakedDate, fakedTime);
        LocalDateTime dt2 = LocalDateTime.of(2013, Month.SEPTEMBER, 22, 15, 40, 44);
        LocalDateTime dt3 = fakedDate.atTime(fakedTime);
        LocalDateTime dt4 = fakedTime.atDate(fakedDate);

        LocalDate d = dt1.toLocalDate();
        LocalTime t = dt2.toLocalTime();
    }


    /**
     * machine time since 1970/01/01 midnight UTC
     */
    public void instant() {
        Instant sec = Instant.ofEpochSecond(3);
        Instant sec2 = Instant.ofEpochSecond(3, 0);
        Instant sec3 = Instant.ofEpochSecond(3, 1_000_000_000);
        Instant sec4 = Instant.ofEpochSecond(4, -1_000_000_000);
    }

    public void manipulateWLocalDateTime() {
        LocalDate fakedDate = LocalDate.parse("2014-12-02");
        LocalTime fakedTime = LocalTime.parse("13:23:55");
        LocalDateTime dt = LocalDateTime.of(fakedDate, fakedTime);
        var dt2 = dt.with(ChronoField.ALIGNED_WEEK_OF_MONTH, 2);
        var dt3 = dt.withHour(0).withMinute(0).withSecond(0).withNano(0);
        var dt4 = dt.withYear(2020).withMonth(10).withDayOfMonth(22);
        var dt5 = dt.plusWeeks(1);
        var dt6 = dt.minusWeeks(3);
        var dt7 = LocalDate.of(1970, 1, 1);
        var dt8 = dt.plus(2, ChronoUnit.YEARS);
    }

    public void temporalAdjustor() {
        var dt = LocalDate.now();
        var nextWorkDay = dt.with(nextOrSame(DayOfWeek.SUNDAY));
        var lastDayOfThisMonth = dt.with(lastDayOfMonth());
    }
}

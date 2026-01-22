package com.java_inaction.time_api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class DateTimeFormatterImpl {

    public static void formattedDate() {
        LocalDate localDate = LocalDate.now();
        String s1 = localDate.format(ISO_LOCAL_DATE);
        String s2 = localDate.format(BASIC_ISO_DATE);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(localDate);
    }

    public static void main(String[] args) {
        formattedDate();
        dateTimeFormatterBuilder();
    }

    public void formattedDateByParse() {
        var dt = LocalDate.parse("20200311", ISO_LOCAL_DATE);
        var dt2 = LocalDate.parse("20200311", BASIC_ISO_DATE);
        String s = dt.format(ISO_LOCAL_DATE);
        String s2 = dt.format(BASIC_ISO_DATE);
    }

    public void formattedDateByCustomerizedFormatter() {
        var dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var dt = LocalDate.of(2022, 1, 31);
        String formattedDate = dtf.format(dt);
        var parsedDT = dtf.parse(formattedDate);
        var parsedByDate = LocalDate.parse(formattedDate, dtf);
    }

    public static void dateTimeFormatterBuilder() {
        var italianFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.CHINESE);
        var dt = LocalDate.parse("20260122", italianFormatter);
        System.out.println(dt);
    }
}

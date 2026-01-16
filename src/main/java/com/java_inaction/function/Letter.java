package com.java_inaction.function;

public class Letter {
    public static String addHeader(String text) {
        return String.format("This is java in action, attention to the class %s", text);
    }

    public static String checkSpelling(String text) {
        return text.replaceAll("lamda", "lambda");
    }

    public static String addFooter(String text) {
        return String.format("%s, kind regards", text);
    }
}

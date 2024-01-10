package edu.hw5;

import java.util.regex.Pattern;

public class Task5 {

    private Task5() {}

    private static final Pattern PATTERN = Pattern.compile("^[А-Я]\\d{3,3}[А-Я][А-Я]\\d{3,3}$");

    public static Pattern get() {
        return PATTERN;
    }
}

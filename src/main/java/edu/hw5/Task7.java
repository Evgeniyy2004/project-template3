package edu.hw5;

import java.util.regex.Pattern;

public class Task7 {
    public static final Pattern one = Pattern.compile("(0|1)(0|1)0(0|1){0,}");
    public  static final Pattern two = Pattern.compile("^((0|1){1,})\\1$");

    public static final Pattern three = Pattern.compile("(0|1){1,3}");
}

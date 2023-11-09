package edu.hw5;

import java.util.regex.Pattern;

public class Task7 {

    private Task7() {}

    public static final Pattern ONE = Pattern.compile("^(0|1)(0|1)0(0|1){0,}$");

    public  static final Pattern TWO = Pattern.compile("^(0|1)((0|1){1,}\\1|$)$");
    public static final Pattern THREE = Pattern.compile("^(0|1){1,3}$");
}

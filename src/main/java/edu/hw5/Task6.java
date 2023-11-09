package edu.hw5;

import java.util.regex.Pattern;

public class Task6 {

    private Task6() {}

    public static boolean partOf(String s, String t) {
        Pattern pattern = Pattern.compile("");
        for (int i = 0; i < s.length(); i++) {
            pattern = Pattern.compile(pattern.pattern() + ".*" + s.charAt(i));
        }
        pattern = Pattern.compile(pattern.pattern() + ".*");
        return pattern.matcher(t).find();
    }
}

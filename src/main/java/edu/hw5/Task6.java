package edu.hw5;

import java.util.regex.Pattern;

public class Task6 {
    public static boolean partOf(String S, String T) {
        Pattern pattern = Pattern.compile("");
        for (int i = 0; i < S.length(); i++) {
            pattern = Pattern.compile(pattern.pattern() + ".*"+S.charAt(i));
        }
        pattern = Pattern.compile(pattern.pattern() + ".*");
        return pattern.matcher(T).find();
    }
}

package edu.hw3;

import org.jetbrains.annotations.NotNull;

public class Task1 {

    private Task1() {

    }

    public static String atbash(@NotNull String str) {
        char[] result = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            int curr = Character.toLowerCase(str.charAt(i)) - 'a';
            if (curr >= 0 && curr <= ('z' - 'a')) {
                result[i] = (char) ('z' - (curr));
            }
        }
        for (int i = 0; i < result.length; i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                result[i] = Character.toUpperCase(result[i]);
            }
        }
        return new String(result);
    }
}

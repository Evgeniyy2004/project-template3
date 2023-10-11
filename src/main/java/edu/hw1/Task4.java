package edu.hw1;

import org.jetbrains.annotations.NotNull;

public final class Task4 {
    private Task4() {
        //not used
    }

    public static @NotNull String fixString(@NotNull String str) {
        char[] result = new char[str.length()];
        for (int i = 1; i < str.length(); i += 2) {
            result[i] = str.charAt(i - 1);
            result[i - 1] = str.charAt(i);
        }
        if (str.length() % 2 != 0) {
            result[str.length() - 1] = str.charAt(str.length() - 1);
        }
        return String.valueOf(result);
    }
}

package edu.hw5;

import org.jetbrains.annotations.NotNull;
import java.util.regex.Pattern;

public class Task4 {
    public static boolean check(@NotNull String string) {
        Pattern pattern =Pattern.compile("[~!@#$%^&*|](.*)");
        Pattern pattern2 =Pattern.compile("(.*)[~!@#$%^&*|]");
        return  pattern.matcher(string).find() || pattern2.matcher(string).find();
    }
}

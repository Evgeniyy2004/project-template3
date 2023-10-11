package edu.hw1;

import org.jetbrains.annotations.NotNull;
import static java.lang.Integer.parseInt;

public final class Task1 {

    private Task1() {
        //not called
    }
    public static int minutesToSeconds(@NotNull  String video) {
        String[] minutesSec = String.join("", video.split(" ")).split(":");
        if (minutesSec.length != 2) {
            return -1;
        }

        try {
            int seconds = parseInt(minutesSec[1]);
            int minutes = parseInt(minutesSec[0]);
            if (seconds >= 60 || seconds < 0 || minutes < 0) {
                return -1;
            }
            return minutes * 60 + seconds;
        } catch (Exception e) {
            return -1;
        }
    }
}

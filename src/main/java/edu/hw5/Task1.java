package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;


public class Task1 {

    private Task1() {

    }

    public static Duration averageTime(@NotNull String[] all) {
        var allDurations = Arrays.stream(all).map(x -> {
            var s = String.join("", x.split(" "));
            int now = 0;
            for (int i = 0; i < s.length(); i++) {
                if (now == 2 && s.charAt(i) == '-') {
                    now = i;
                    break;
                } else if (s.charAt(i) == '-') {
                    now++;
                }
            }
            var one = String.join(" ", s.substring(0, now).split(","));
            var two = String.join(" ", s.substring(now + 1, s.length()).split(","));
            var format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            var first = LocalDateTime.parse(one, format);
            var second = LocalDateTime.parse(two, format);
            return Duration.between(first, second);
        }).mapToLong(Duration::toMillis);
        var milliSeconds = allDurations.sum() / all.length;
        return Duration.ofMillis(milliSeconds);

    }
}

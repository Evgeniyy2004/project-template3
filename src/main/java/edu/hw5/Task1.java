package edu.hw5;

import org.jetbrains.annotations.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;

public class Task1 {
    public static Duration averageTime(@NotNull String[] all)  throws ParseException {
        var allDurations = Arrays.stream(all).map(x -> {
            int now = 0;
            for (int i = 0; i < x.length(); i++) {
                if (now == 2 && x.charAt(i) == '-') {
                    now = i;
                    break;
                } else if (x.charAt(i) == '-') {
                    now++;
                }
            }
            var first = LocalDateTime.parse(x.substring(0,now - 1));
            var second = LocalDateTime.parse(x.substring(now +1, x.length() - now - 1));
            return Duration.between(first, second);
            });
        var milliSeconds =  allDurations.mapToLong(Duration::toMillis).sum()/allDurations.count();
        return Duration.ofMillis(milliSeconds);

    }
}

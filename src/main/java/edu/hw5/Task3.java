package edu.hw5;

import org.jetbrains.annotations.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static java.util.Map.entry;

public class Task3 {
    private static final Map<String,Integer> oneWord = Map.ofEntries(
        entry("yesterday", -1),
        entry("tomorrow", 1),
        entry("today",0)
    );
    public static Optional<LocalDate> oneOfFormats(@NotNull String str) {
        Optional<LocalDate> result = Optional.empty();
        try {
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
             var curr = LocalDate.parse(str, formatter);
             return Optional.of(curr);

        } catch (DateTimeParseException exception) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                var curr = LocalDate.parse(str, formatter);
                return Optional.of(curr);
            } catch (DateTimeParseException exception1) {
            }
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            var curr = LocalDate.parse(str, formatter);
            return Optional.of(curr);
        } catch (DateTimeParseException e){
        }
        var curr =str.split(" ");
        if (oneWord.containsKey(curr)) {
            return Optional.of(LocalDate.now().plusDays(oneWord.get(curr)));
        }
        if (Arrays.stream(curr).filter(r -> r.equals("ago")).count() > 0 ) {
            return Optional.of(LocalDate.now().minusDays(Integer.parseInt(curr[0])));
        }
        return result;
    }
}

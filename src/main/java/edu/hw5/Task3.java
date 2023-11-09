package edu.hw5;

import org.jetbrains.annotations.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import static java.util.Map.entry;

public class Task3 {
    private static final Map<String,Integer> oneWord = Map.ofEntries(
        entry("yesterday", -1),
        entry("tomorrow", 1),
        entry("today",0)
    );
    public static Optional<LocalDate> oneOfFormats(@NotNull String str) {
        Optional<LocalDate> result = Optional.empty();
        var currStr = String.join("",str.split(" "));
        try {
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             var curr = LocalDate.parse(currStr, formatter);
             return Optional.of(curr);

        } catch (DateTimeParseException exception) {
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            var curr = LocalDate.parse(currStr, formatter);
            return Optional.of(curr);
        } catch (DateTimeParseException e){
        }
        if (currStr.contains("/")) {
            var first = currStr.split("/")[0].length();
            var second = currStr.split("/")[1].length();
            var third = currStr.split("/")[2].length();
            StringBuilder now = new StringBuilder();
            now.append("d".repeat(first)+"/");
            now.append("M".repeat(second)+"/");
            now.append("y".repeat(third));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(now.toString());
            var curr = LocalDate.parse(currStr, formatter);
            return Optional.of(curr);
        }
        var curr = str.split(" ", -1);
        var stream =Arrays.stream(curr);
        if (oneWord.containsKey(String.join("", curr))) {
            return Optional.of(LocalDate.now().plusDays(oneWord.get(String.join("", curr))));
        }
        if (stream.filter(r -> r.equals("ago")).count() > 0 ) {
            var noEmpties = Stream.of(curr).filter(r -> !r.isEmpty()).toArray();
            return Optional.of(LocalDate.now().minusDays(Integer.parseInt(noEmpties[0].toString())));
        }
        return result;
    }
}

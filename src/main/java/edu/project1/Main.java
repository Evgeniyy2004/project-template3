package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary() {
            @Override
            public @NotNull String randomWord() {
                double a = Math.random();
                try {
                    List<String> list = Files.readAllLines(Paths.get("Words.txt"));
                    return "" + list.get((int)((list.size() - 1) * a));
                } catch (IOException exception) {
                    return "";
                }
            }
        };
        var hangman = new ConsoleHangman(5, dictionary.randomWord());
        hangman.run();
    }
}

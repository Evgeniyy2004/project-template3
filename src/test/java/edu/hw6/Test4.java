package edu.hw6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Test4 {

    @Test
    @DisplayName("В файл должна быть записана строчка Programming is learned by writing programs. ― Brian Kernighan")
    void buffersChain() {
        new Task4().printText(new File("Phrase.txt").toPath());
        var must = "Programming is learned by writing programs. ― Brian Kernighan";
        try {
            var result = Files.readString(new File("Phrase.txt").toPath());
            assertTrue(result.equals(must));
        } catch (IOException e) {
            fail();
        }
    }
}

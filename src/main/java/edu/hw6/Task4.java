package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class Task4 {

    private static final int LIMIT_OF = 100;

    @SuppressWarnings("all")
    void printText(Path path) {
        try {
            Files.createFile(path);
            try (var stream = Files.newOutputStream(path)) {
                try (var checkStream = new CheckedOutputStream(stream, new CRC32())) {
                    try (var buffer = new BufferedOutputStream(checkStream, LIMIT_OF)) {
                        try (var writer = new OutputStreamWriter(buffer, StandardCharsets.UTF_8)) {
                            try(var end = new PrintWriter(writer, true)) {
                                end.write("Programming is learned by writing programs. ― Brian Kernighan");
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            return;
        }
    }
}

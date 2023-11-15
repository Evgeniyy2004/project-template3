package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class RegularFile implements DirectoryStream.Filter<Path> {

    private final String regex;

    public RegularFile(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        return Pattern.compile(regex).matcher(entry.getFileName().toString()).find();
    }

    public DirectoryStream.Filter<java.nio.file.Path> and(DirectoryStream.Filter<java.nio.file.Path> a) {
        return new DirectoryStream.Filter<java.nio.file.Path>() {
            @Override
            public boolean accept(java.nio.file.Path entry) throws IOException {
                return new RegularFile(regex).accept(entry) && a.accept(entry);
            }
        };
    }
}

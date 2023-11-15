package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Readable implements DirectoryStream.Filter<Path> {

    public Readable() {
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        return Files.isReadable(entry);
    }

    public DirectoryStream.Filter<java.nio.file.Path> and(DirectoryStream.Filter<java.nio.file.Path> a) {
        return new DirectoryStream.Filter<java.nio.file.Path>() {
            @Override
            public boolean accept(java.nio.file.Path entry) throws IOException {
                return Files.isReadable(entry) && a.accept(entry);
            }
        };
    }
}

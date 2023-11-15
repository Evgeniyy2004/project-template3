package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class LargerThan implements DirectoryStream.Filter<Path> {

    public final long size;

    public LargerThan(long size) {
        this.size = size;
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        return Files.size(entry) > size;
    }

    public DirectoryStream.Filter<java.nio.file.Path> and(DirectoryStream.Filter<java.nio.file.Path> a) {
        return new DirectoryStream.Filter<java.nio.file.Path>() {
            @Override
            public boolean accept(java.nio.file.Path entry) throws IOException {
                return Files.size(entry) > size && a.accept(entry);
            }
        };
    }
}

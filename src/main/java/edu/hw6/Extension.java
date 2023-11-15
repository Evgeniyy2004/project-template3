package edu.hw6;

import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public class Extension implements  DirectoryStream.Filter<Path>{

    private final String GLOB;
    public Extension(@NotNull String glob) {
        GLOB = glob;
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        var extension = FilenameUtils.getExtension(entry.toString());
        return (extension.equals(GLOB));

    }

    public DirectoryStream.Filter<java.nio.file.Path> and(DirectoryStream.Filter<java.nio.file.Path> a) {
        return new DirectoryStream.Filter<java.nio.file.Path>() {
            @Override
            public boolean accept(java.nio.file.Path entry) throws IOException {
                return new Extension(GLOB).accept(entry) && a.accept(entry);
            }
        };
    }
}

package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;

public class Extension implements DirectoryStream.Filter<Path> {

    private final String glob;

    public Extension(@NotNull String glob) {
        this.glob = glob;
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        var extension = FilenameUtils.getExtension(entry.toString());
        return (extension.equals(glob));

    }

    public DirectoryStream.Filter<java.nio.file.Path> and(DirectoryStream.Filter<java.nio.file.Path> a) {
        return new DirectoryStream.Filter<java.nio.file.Path>() {
            @Override
            public boolean accept(java.nio.file.Path entry) throws IOException {
                return new Extension(glob).accept(entry) && a.accept(entry);
            }
        };
    }
}

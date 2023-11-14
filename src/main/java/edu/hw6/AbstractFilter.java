package edu.hw6;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;

public class AbstractFilter  {
    // TODO

    public static DirectoryStream.Filter<Path> magicNumber(char [] start) {
        return new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                try (InputStream is = new FileInputStream(String.valueOf(entry))) {
                    byte[] bytes = new byte[is.readAllBytes().length];
                    is.read(bytes);
                    if (bytes.length < start.length) return false;
                    for (int i = 0; i < start.length; i++) {
                        if (start[i] != (char) bytes[i]) return false;
                    }
                    return true;
                }
            }
        };
    }

    public static DirectoryStream.Filter<Path> regularFile(String regex) {
        return new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return Pattern.compile(regex).matcher(entry.getFileName().toString()).find();
            }
        };
    }
    public static DirectoryStream.Filter<Path> largerThan(Long size) {
        return new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return Files.size(entry) > size;
            }
        };
    }



    public static DirectoryStream.Filter<Path> writeable() {
        return new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return Files.isWritable(entry);
            }
        };
    }

    public static DirectoryStream.Filter<Path> readable() {
       return new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return Files.isReadable(entry);
            }
        };
    }

    public static DirectoryStream.Filter<Path> extension(@NotNull String glob) {
        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                var extension = FilenameUtils.getExtension(entry.toString());
                return (extension.replaceAll("\\*","").equals(entry.toString()));
            }
        };
        return filter;
    }

    public DirectoryStream.Filter<Path> and(DirectoryStream.Filter<Path> a) {
       return new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return this.accept(entry) && a.accept(entry);
            }
        };
    }


}


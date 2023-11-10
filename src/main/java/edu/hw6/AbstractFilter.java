package edu.hw6;

import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class AbstractFilter implements DirectoryStream.Filter<Path> {
    // TODO

    public Long SIZE;
    public Path path;
    public String EXTENSION;
    public Pattern REGEX;
    public Boolean READ;
    public Boolean WRITE;
    public char[] MAGIC;

    public AbstractFilter(
        @NotNull Path path, long size, String glob,
        Pattern regex, Boolean readable, Boolean writeable, char[] magic
    ) {
        this.path = path;
        SIZE = size;
        EXTENSION = glob;
        REGEX = regex;
        READ = readable;
        WRITE = writeable;
        MAGIC = magic;
    }

    public final Predicate<Path> regularFile = path -> {
        if (this.REGEX == null) {
            return true;
        }
        return REGEX.matcher(path.getFileName().toString()).find();
    };
    public static final Predicate<Path> readable = Files::isReadable;
    public final Predicate<Path> largerThan = x -> {
        try {
            return Files.size(x) > this.SIZE;
        } catch (IOException e) {
            return false;
        }
    };

    public final Predicate<Path> magicNumber = x -> {
        if (MAGIC == null) {
            return true;
        }
        for (char c : MAGIC) {
            if (x.toString().contains(""+c)) {
                return true;
            }
        }
        return false;
    };

    public final Predicate<Path> globMatches = x -> {
        if (EXTENSION == null) return true;
        var extension = FilenameUtils.getExtension(x.toString());
        return (extension.equals(EXTENSION));
    };



    DirectoryStream.Filter<Path> filter = regularFile
        .and(readable)
        .and(largerThan)
        .and(magicNumber)
        .and(globMatches);

    @Override
    public boolean accept(Path entry) throws IOException {
        return false;
    }
}


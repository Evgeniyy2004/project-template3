package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

public class AbstractFilter implements DirectoryStream.Filter<Path> {
        // TODO

        public AbstractFilter(long size, String glob, boolean readable, boolean writeable, ) {

        }
        public static final Predicate<Path>  regularFile = Files::isRegularFile;
        public static final Predicate<Path> readable = Files::isReadable;

        DirectoryStream.Filter<Path> filter = regularFile
            .and(readable)
            .and(largerThan(100000))
            .and(magicNumber(0x89, 'P', 'N', 'G'))
            .and(globMatches("*.png"))
            .and(regexContains("[-]"));




    @Override
    public boolean accept(Path entry) throws IOException {
        return false;
    }
}


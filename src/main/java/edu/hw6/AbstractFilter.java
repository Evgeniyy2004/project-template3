package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class AbstractFilter implements DirectoryStream.Filter<Path> {
        // TODO

        public AbstractFilter(long size, String glob, ) {

        }
        public static final AbstractFilter regularFile = Files::isRegularFile;
        public static final AbstractFilter readable = Files::isReadable;

        DirectoryStream.Filter<Path> filter = regularFile
            .and(readable)
            .and(largerThan(100000))
            .and(magicNumber(0x89, 'P', 'N', 'G'))
            .and(globMatches("*.png"))
            .and(regexContains("[-]"));


        public boolean largerThan(long maybe) {
            if ()
        }

    @Override
    public boolean accept(Path entry) throws IOException {
        return false;
    }
}


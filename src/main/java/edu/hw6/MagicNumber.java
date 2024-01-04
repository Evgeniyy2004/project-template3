package edu.hw6;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import org.jetbrains.annotations.NotNull;

public class MagicNumber implements DirectoryStream.Filter<java.nio.file.Path> {

    public byte[] args;

    public MagicNumber(@NotNull byte[] args) {
        this.args = args;
    }

    @Override
    public boolean accept(java.nio.file.Path entry) throws IOException {
        try (InputStream is = new FileInputStream(String.valueOf(entry))) {
            byte[] bytes = is.readAllBytes();
            if (bytes.length < args.length) {
                return false;
            }
            for (int i = 0; i < args.length; i++) {
                if (args[i] != bytes[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    public DirectoryStream.Filter<java.nio.file.Path> and(DirectoryStream.Filter<java.nio.file.Path> a) {
        return new DirectoryStream.Filter<java.nio.file.Path>() {
            @Override
            public boolean accept(java.nio.file.Path entry) throws IOException {
                return new MagicNumber(args).accept(entry) && a.accept(entry);
            }
        };
    }
}

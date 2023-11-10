package edu.hw6;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

public class Task2 {

    private Task2() {
    }

    public void cloneFile(Path path) {
        var directory = new File(path.getParent().toString());
        var stream = Arrays.stream(directory.listFiles());

    }
}

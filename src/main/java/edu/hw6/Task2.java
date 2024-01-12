package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.commons.io.FilenameUtils;
import static org.apache.commons.io.FileUtils.directoryContains;

public class Task2 {

    private Task2() {
    }

    public static void cloneFile(Path path) throws IOException {
        var currName = path.getFileName().toString();
        var currToAdd = "-копия";
        var i = currName.lastIndexOf('.');
        currName = currName.substring(0, i);
        var extension = FilenameUtils.getExtension(path.toString());
        int currdigit = 0;
        var curr = currName + currToAdd + "." + extension;
        if (!directoryContains(path.toAbsolutePath().getParent().toFile(), Paths.get(curr).toFile())) {
            Files.copy(path, Paths.get(curr), StandardCopyOption.REPLACE_EXISTING);
            return;
        }
        currdigit += 1;
        curr = currName + currToAdd + "(" + currdigit + ")" + "." + extension;
        while (directoryContains(path.toAbsolutePath().getParent().toFile(), Paths.get(curr).toFile())) {
            currdigit += 1;
            curr = currName + currToAdd + "(" + currdigit + ")" + "." + extension;
        }
        Files.copy(path, Paths.get(curr), StandardCopyOption.REPLACE_EXISTING);
    }
}

package edu.hw6;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import org.apache.commons.io.FilenameUtils;
import static org.apache.commons.io.FileUtils.directoryContains;

public class Task2 {

    private Task2() {
    }

    public void cloneFile(Path path) throws IOException {
        //var directory = new File(path.getParent().toString());
        var currName = path.getFileName().toString();
        var currToAdd = "-копия";
        var i = currName.lastIndexOf('.');
        currName = currName.substring(0,i);
        var extension = FilenameUtils.getExtension(path.toString());
        int currdigit = 0;
        var curr = currName + currToAdd + extension;
        if (!directoryContains(path.getParent().toFile(), Paths.get(curr).toFile())) {
            Files.copy(path, Paths.get(curr), StandardCopyOption.REPLACE_EXISTING);
            return;
        }
        currdigit += 1;
        curr = currName + currToAdd +"(" + currdigit+")"+ extension;
        while (directoryContains(path.getParent().toFile(), Paths.get(curr).toFile())) {
           currdigit += 1;
           curr = currName + currToAdd +"(" + currdigit+")"+ extension;
        }
        Files.copy(path, Paths.get(curr), StandardCopyOption.REPLACE_EXISTING);
    }
}

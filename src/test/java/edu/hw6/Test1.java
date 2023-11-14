package edu.hw6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.apache.commons.io.FileUtils.directoryContains;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Test1 {
    @Test
    @DisplayName("DiskMap может создаваться с помощью конструктора без параметров и на основе текстового файла. Изменение DiskMap ведет к изменению файла")
    void Test1() {
        var disk = new DiskMap();
        var newDisk = new DiskMap();
        try {
            assertTrue(directoryContains(disk.path.getParent().toFile(), new File("DiskMap.txt")));
            assertTrue(directoryContains(newDisk.path.getParent().toFile(), new File("DiskMap1.txt")));
            disk.path.toFile().delete();
            newDisk.path.toFile().delete();
            disk = new DiskMap(new File("DiskMap.txt").toPath());
            assertTrue(disk.containsKey("строка"));
            assertTrue(disk.containsValue("не знаю"));

            disk.put("nothing", "special");
            var curr = Files.readAllLines(disk.path);
            assertTrue(curr.contains("nothing:special"));

            disk.remove("nothing");
            curr = Files.readAllLines(disk.path);
            assertFalse(curr.contains("nothing:special"));
        } catch (IOException e) {
            fail();
        }
    }


}

package edu.hw6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.apache.commons.io.FileUtils.directoryContains;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Test2 {
    @Test
    @DisplayName("cloneFile добавляет к имени файла слово \'копия\' и номер последней версии файла плюс единица (начиная с 1), содержание всех файлов одинаково")
    void checkCloning() {
        try {
            Task2.cloneFile(new File("DiskMap.txt").toPath());
            var file = new File("DiskMap-копия.txt");
            assertTrue(directoryContains(new File("C:\\Users\\user\\IdeaProjects\\project-template3"), file));
            Task2.cloneFile(new File("DiskMap.txt").toPath());
            file = new File("DiskMap-копия(1).txt");
            assertTrue(directoryContains(new File("C:\\Users\\user\\IdeaProjects\\project-template3"), file));

            //сравнение содержимого файлов
            var one = Files.readAllLines(file.toPath());
            var two = Files.readAllLines(new File("DiskMap.txt").toPath());
            var three = Files.readAllLines(new File("DiskMap-копия(1).txt").toPath());
            for (int i =0; i < one.size(); i++) {
                if (!one.get(i).equals(two.get(i))) fail();
                if (!three.get(i).equals(two.get(i))) fail();
            }
        } catch (IOException e) {
            fail();
        }
    }
}

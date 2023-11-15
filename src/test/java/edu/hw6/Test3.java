package edu.hw6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Test3 {


    @Test
    @DisplayName("Каждая из реализаций DirectoryStream.Filter проверяет соответствие файла одному из признаков")
    void regexFilterCheck() {
        var withRegex = new RegularFile("a");
        var second = new RegularFile("^mvnw$");
        try {
            assertTrue(withRegex.accept(new File("DiskMap.txt").toPath()));
            assertTrue(withRegex.accept(new File(".gitattributes").toPath()));
            assertFalse(withRegex.accept(new File("pom.xml").toPath()));
            assertFalse(withRegex.accept(new File("README.md").toPath()));

            assertTrue(second.accept(new File("mvnw").toPath()));
            assertFalse(withRegex.accept(new File("mvnw.cmd").toPath()));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    @DisplayName("Фильтр, создаваемый методом largerThan сравнивает размер файла в байтах и данное ограничение по размеру")
    void sizeFilterCheck() {
        var filter = new LargerThan(2048L);
        try {
            assertTrue(filter.accept(new File(".editorconfig").toPath()));
            assertFalse(filter.accept(new File(".gitignore").toPath()));
            assertFalse(filter.accept(new File(".gitattributes").toPath()));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    @DisplayName("writeable и readable создают фильтры, определяющие доступен ли файл для записи или чтения")
    void ableFilterCheck() {
        var one = new Readable();
        var second = new Writeable();
        try {
            assertTrue(one.accept(new File("DiskMap.txt").toPath()));
            assertTrue(one.accept(new File(".gitattributes").toPath()));

            assertTrue(second.accept(new File("README.md").toPath()));
            assertTrue(second.accept(new File("mvnw").toPath()));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    @DisplayName("Extension создает фильтр, проверяющий, совпадают ли заданное в качестве параметра расширение и расширение файла")
    void extensionFilterCheck() {
        var one = new Extension("xml");
        try {
            assertFalse(one.accept(new File("DiskMap.txt").toPath()));
            assertFalse(one.accept(new File("mvnw").toPath()));
            assertTrue(one.accept(new File("pom.xml").toPath()));
            assertTrue(one.accept(new File("pom.xml").toPath()));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    @DisplayName("Фильтр MagicNumber проверяет начальные байты файла")
    void magicNumberFilterCheck() {
        var one = new MagicNumber(new byte[]{(byte)0x89,'P', 'N','G'});
        try {
            assertTrue(one.accept(new File("img.png").toPath()));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    @DisplayName("Каждый из фильтров может быть объединен с другим фильтром")
    void unityOfFilters() {
        var filter1 = new LargerThan(1023L);
        var resFilter = filter1.and(new Extension("cmd"));
        var directory = new File("C:\\Users\\user\\IdeaProjects\\project-template3").toPath();

        try {
            try (var result = Files.newDirectoryStream(directory, resFilter)) {
                int i = 0;
                for (Path c : result) {
                    if (i == 2) fail();
                    if (Files.size(c) <= filter1.SIZE) fail();
                    if (!FilenameUtils.getExtension(c.toString()).equals("cmd")) fail();
                    i++;
                }
            }
        } catch (IOException e) {
        }

    }
}

package edu.hw6;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Test3 {


    @Test
    @DisplayName("Каждая из реализаций DirectoryStream.Filter проверяет соответствие файла одному из признаков")
    void regexFilterCheck() {
        var withRegex = AbstractFilter.regularFile("a");
        var second = AbstractFilter.regularFile("^mvnw$");
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
        var filter = AbstractFilter.largerThan(2048L);
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
        var one = AbstractFilter.readable();
        var second = AbstractFilter.writeable();
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
    @DisplayName("extension создает фильтр, проверяющий, совпадают ли заданное в качестве параметра расширение и расширение файла")
    void extensionFilterCheck() {
        var one = AbstractFilter.extension("xml");
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
    @DisplayName("Фильтр, создаваемый метод magicNumber проверяет ")
    void magicNumberFilterCheck() {
        var one = AbstractFilter.magicNumber(new byte[]{89,'P', 'N','G'});
        try {
            assertTrue(one.accept(new File("img.png").toPath()));
        } catch (IOException e) {
            fail();
        }
    }
}

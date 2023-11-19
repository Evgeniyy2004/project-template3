package edu.project3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

public class LogTest {

    @Test
    @DisplayName("При указании формата markdown или отсутствии формата информация выводится в консоль в формате markdown")
    void checkMarkDown() {
            String one = "java -jar nginx-log-stats.jar --path https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs --from 2023-08-31 --format markdown";
            try {
                LogAnalyst.ngixStats(one);
            } catch (IOException | ParseException e) {
                fail();
            }
    }

    @Test
    @DisplayName("Если в качестве формата указан adoc создается либо удаляется и создается заново файл result.adoc")
    void checkAdoc() {
        String one = "java -jar nginx-log-stats.jar --path https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs --from 2023-08-31 --format adoc";
        try {
            LogAnalyst.ngixStats(one);
            var now = Files.readAllBytes(new File("result.adoc").toPath());
            System.out.println(new String(now, "UTF-8"));
        } catch (IOException | ParseException e) {
            fail();
        }
    }

    @Test
    @DisplayName("Если в качестве формата указан adoc создается либо удаляется и создается заново файл result.adoc")
    void workWithFiles() {
        String one = "java -jar nginx-log-stats.jar --path C:\\Users\\user\\Documents\\logi\\logi2.txt.txt --from 2023-08-31 --format adoc";
        try {
            LogAnalyst.ngixStats(one);
            var now = Files.readAllBytes(new File("result.adoc").toPath());
            System.out.println(new String(now, "UTF-8"));
        } catch (IOException | ParseException e) {
            fail();
        }
    }
}

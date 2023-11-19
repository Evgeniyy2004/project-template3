package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.text.ParseException;

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
}

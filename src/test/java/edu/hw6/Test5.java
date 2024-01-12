package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class Test5 {

    @Test
    @DisplayName(
        "hackerNewsTopStories возвращает массив с идентификаторами наиболее обсуждаемых статей, news возвращает имя новости с заданным идентификатором")
    void check() {
        var task = new HackerNews();
        try {
            assertThat(task.news(37570037)).isEqualTo("JDK 21 Release Notes");
            task.hackerNewsTopStories();
        } catch (IOException | URISyntaxException e) {
            fail();
        }
    }
}

package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test6 {
    @Test
    @DisplayName(
        "hackerNewsTopStories возвращает массив с идентификаторами наиболее обсуждаемых статей, news возвращает имя новости с заданным идентификатором")
    void check() {
            int j = (int) (Math.random()*65535);
            if (j <= 49151) {
                assertTrue(Task6.check(j));
            } else {
                assertFalse(Task6.check(j));
            }
    }
}

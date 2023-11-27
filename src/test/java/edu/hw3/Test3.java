package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task2.clusterize;
import edu.hw3.Task3;
import java.security.KeyException;
import java.util.NoSuchElementException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test3 {
    @Test
    @DisplayName("Пустой список - пустой словарь")
    void noWords() {
        assertTrue(new Task3().freqDict(new String[0]).isEmpty());
    }

    @Test
    @DisplayName("Частотный словарь создается для любых типов данных")
    void notOnlyStrings() {
        var firstExample = new String[] {"a", "bb", "a", "bb"};
        var firstResult = new Task3().freqDict(firstExample);
        assertThat(firstResult.size()).isEqualTo(2);
        assertThat(firstResult.get("a")).isEqualTo(2);
        assertThat(firstResult.get("bb")).isEqualTo(2);

        var secondExample = new String[] {"this", "and", "that", "and"};
        var secondResult = new Task3().freqDict(secondExample);
        assertThat(secondResult.size()).isEqualTo(3);
        assertThat(secondResult.get("this")).isEqualTo(1);
        assertThat(secondResult.get("and")).isEqualTo(2);
        assertThat(secondResult.get("that")).isEqualTo(1);

        var thirdExample = new Double[] {78.0, 56.99999, 56.9, 78.0,10.5};
        var thirdResult = new Task3().freqDict(thirdExample);
        assertThat(thirdResult.size()).isEqualTo(4);
        assertThat(thirdResult.get(78.0)).isEqualTo(2);
        assertThat(thirdResult.get(56.9)).isEqualTo(1);
        assertThat(thirdResult.get(56.99999)).isEqualTo(1);
        assertThat(thirdResult.get(10.5)).isEqualTo(1);
        assertNull(thirdResult.get(11.0));
    }
}

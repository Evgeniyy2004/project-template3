package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test8 {

    @Test
    @DisplayName("Проверка работы итератора с пустой коллекцией")
    void empty() {
        var second = new Task8<String>(Arrays.stream(new String[0]).toList());
        assertFalse(second.hasNext());
        try {
            second.next();
            assertTrue(false);
        } catch (IndexOutOfBoundsException exception) {
            assertTrue(true);
        }
    }

    @Test
    @DisplayName("Возврат коллекций разных типов данных в обратном порядке")
    void iteratingDifferent() {
        var first = new Task8<String>(Arrays.stream(new String[] {"ee","hehe", "aaaaa", "whyyy"}).toList());
        assertThat(first.next()).isEqualTo("whyyy");
        assertThat(first.next()).isEqualTo("aaaaa");
        assertThat(first.next()).isEqualTo("hehe");
        assertThat(first.next()).isEqualTo("ee");
        assertFalse(first.hasNext());

        var last =  new Task8<Double>(Arrays.stream(new Double[]{7.5, 7.8, 8.9}).toList());
        assertThat(last.next()).isEqualTo(8.9);
        assertThat(last.next()).isEqualTo(7.8);
        assertThat(last.next()).isEqualTo(7.5);
        assertFalse(first.hasNext());

    }
}

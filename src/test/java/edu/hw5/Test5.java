package edu.hw5;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.hw5.Task5;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test5 {
    @Test
    @DisplayName("Проверка автомобильных номеров")
    void checkNumbers() {
        assertTrue(Task5.get().matcher("А123ВЕ777").find());
        assertTrue(Task5.get().matcher("О777ОО177").find());

        assertFalse(Task5.get().matcher("123АВЕ777").find());
        assertFalse(Task5.get().matcher("А123ВГ77").find());
        assertFalse(Task5.get().matcher("а123ВЕ7777").find());
    }
}

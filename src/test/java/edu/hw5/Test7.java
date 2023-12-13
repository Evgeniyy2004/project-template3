package edu.hw5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test7 {
    @Test
    @DisplayName("Длина проверяемой строки из алфавита {0,1} не менее 1 и не более 3")
    void more1Less3() {
        assertFalse(Task7.THREE.matcher("0111").find());
        assertTrue(Task7.THREE.matcher("0").find());
        assertTrue(Task7.THREE.matcher("1").find());
        assertTrue(Task7.THREE.matcher("000").find());
    }

    @Test
    @DisplayName("Одинаковые символы из алфавита {0,1} в начале и конце строки")
    void equalSymbols() {
        assertFalse(Task7.TWO.matcher("0111").find());
        assertTrue(Task7.TWO.matcher("0").find());
        assertTrue(Task7.TWO.matcher("1").find());
        assertTrue(Task7.TWO.matcher("000").find());
        assertFalse(Task7.TWO.matcher("0111").find());
        assertFalse(Task7.TWO.matcher("200001111112").find());
    }

    @Test
    @DisplayName("Длина строки не менее 3 и третий символ строки равен нулю")
    void everyThirdIsZero() {
        assertFalse(Task7.ONE.matcher("0111").find());
        assertFalse(Task7.ONE.matcher("0").find());
        assertTrue(Task7.ONE.matcher("100").find());
        assertTrue(Task7.ONE.matcher("000").find());
        assertFalse(Task7.ONE.matcher("0111").find());
        assertFalse(Task7.ONE.matcher("200000111112").find());
    }
}

package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test5 {

    @Test
    @DisplayName("Число длиной 1 не является палиндромом")
    void oneDigitTest() {
        assertFalse(Task5.isPalindromeDescendant(6));
    }

    @Test
    @DisplayName("Число с нечетным количеством цифр не имеет потомков")
    void noDescedantTest() {
        assertTrue(Task5.isPalindromeDescendant(56765));
        assertFalse(Task5.isPalindromeDescendant(67867));
    }

    @Test
    @DisplayName("Определение палиндрома среди чисел четной длины")
    void evenTest() {
        assertTrue(Task5.isPalindromeDescendant(11211230));
        assertTrue(Task5.isPalindromeDescendant(-786787));
        assertFalse(Task5.isPalindromeDescendant(3423));
        assertTrue(Task5.isPalindromeDescendant(-11211230));
        assertFalse(Task5.isPalindromeDescendant(564858));
    }

}

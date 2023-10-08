package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test5 {

    @Test
    @DisplayName("Число длиной 1 не является палиндромом")
    void oneDigitTest() {
        assertThat(Task5.isPalindromeDescendant(6)).isEqualTo(false);
    }

    @Test
    @DisplayName("Число с нечетным количеством цифр не имеет потомков")
    void noDescedantTest() {
        assertThat(Task5.isPalindromeDescendant(56765)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(67867)).isEqualTo(false);
    }

    @Test
    @DisplayName("Определение палиндрома среди чисел четной длины")
    void evenTest() {
        assertThat(Task5.isPalindromeDescendant(11211230)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(-786787)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(3423)).isEqualTo(false);
        assertThat(Task5.isPalindromeDescendant(-11211230)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(564858)).isEqualTo(false);
    }

}

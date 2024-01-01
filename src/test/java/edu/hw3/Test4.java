package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task4.convertToRoman;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test4 {
    @Test
    @DisplayName("Ноль и отрицательные числа не конвертируются в римскую запись")
    void noConvert() {
        try {
            convertToRoman(-4);
            assertTrue(false);
        } catch (IllegalArgumentException exception) {
            assertTrue(true);
        }

        try {
            convertToRoman(0);
            assertTrue(false);
        } catch (IllegalArgumentException exception) {
            assertTrue(true);
        }
    }

    @Test
    @DisplayName("Проверка конвертирования табличных цифр")
    void convertTableNumbers() {
        assertThat(convertToRoman(1000)).isEqualTo("M");
        assertThat(convertToRoman(500)).isEqualTo("D");
        assertThat(convertToRoman(100)).isEqualTo("C");
        assertThat(convertToRoman(4)).isEqualTo("IV");
    }

    @Test
    @DisplayName("Проверка конвертирования нетабличных цифр")
    void notSimpleNumbers() {
        assertThat(convertToRoman(12)).isEqualTo("XII");
        assertThat(convertToRoman(116)).isEqualTo("CXVI");
        assertThat(convertToRoman(2)).isEqualTo("II");
        assertThat(convertToRoman(1478)).isEqualTo("MCDLXXVIII");
    }
}

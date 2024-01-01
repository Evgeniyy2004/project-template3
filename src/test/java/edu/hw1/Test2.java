package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test2 {

    @Test
    @DisplayName("Расчет количества цифр у нуля, положительных и отрицательных чисел")
    void correctNumbersTest() {
        // given
        int[] numbers = {0040 , -708 , 0000 , 0, 56};
        // when
        int[] result = new int[5];
        for (int i = 0; i < 5; i++) result[i] = Task2.countDigits(numbers[i]);
        //then
        assertThat(result).containsExactly(2, 3, 1, 1, 2);
    }
}

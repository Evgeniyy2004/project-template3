package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test6 {

    @Test
    @DisplayName("Для чисел, абсолютная величина которых не входит в диапазон [1001, 9998]  число 6174 недостижимо")
    void outOfBoundsTest() {
        assertThat(Task6.countK(1000)).isEqualTo(-1);
        assertThat(Task6.countK(-10000)).isEqualTo(-1);
        assertThat(Task6.countK(344)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Для чисел, все цифры которых одинаковы, число 6174 также недостижимо")
    void allDigitsAreTheSameTest() {
        assertThat(Task6.countK(9999)).isEqualTo(-1);
        assertThat(Task6.countK(3333)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Число 6174 достигается из самого себя за 0 шагов")
    void noStepsTest() {
        assertThat(Task6.countK(6174)).isEqualTo(0);
    }

    @Test
    @DisplayName("Определение количества шагов для четырехзначных чисел")
    void someStepsTest() {
        assertThat(Task6.countK(3524)).isEqualTo(3);
        assertThat(Task6.countK(6621)).isEqualTo(5);
        assertThat(Task6.countK(6554)).isEqualTo(4);
        assertThat(Task6.countK(1234)).isEqualTo(3);
    }
}

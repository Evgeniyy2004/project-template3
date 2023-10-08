package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test7 {

    @Test
    @DisplayName("Ноль и единица неизменимы")
    void noRotateTest() {
        assertThat(Task7.rotateLeft(0,1)).isEqualTo(0);
        assertThat(Task7.rotateRight(1,78)).isEqualTo(1);
        assertThat(Task7.rotateLeft(-1,-56)).isEqualTo(1);
    }

    @Test
    @DisplayName("Отрицательное значение параметра shift меняет направление сдвига, отрицательное значение параметра n ни на что не влияет")
    void lessThanZeroRotateTest() {
        assertThat(Task7.rotateRight(17,-2)).isEqualTo(6);
        assertThat(Task7.rotateRight(-17,-2)).isEqualTo(6);
        assertThat(Task7.rotateRight(8,1)).isEqualTo(4);
    }
}

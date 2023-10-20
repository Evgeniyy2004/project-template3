package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test4 {

    @Test
    @DisplayName("Исправление пустой строки и строки длиной 1")
    void edgeTest() {
        assertThat(Task4.fixString("")).isEqualTo("");
        assertThat(Task4.fixString("4")).isEqualTo("4");
    }

    @Test
    @DisplayName("Исправление строк с пробелами")
    void spaceTest() {
        assertThat(Task4.fixString("8765y e")).isEqualTo("7856 ye");
        assertThat(Task4.fixString("ot oahdr")).isEqualTo("too hard");
        assertThat(Task4.fixString("hTsii  s aimex dpus rtni.g")).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("Исправление строк четной и нечетной длины")
    void simpleTest() {
        assertThat(Task4.fixString("olevajav")).isEqualTo("lovejava");
        assertThat(Task4.fixString("21435")).isEqualTo("12345");
    }
}

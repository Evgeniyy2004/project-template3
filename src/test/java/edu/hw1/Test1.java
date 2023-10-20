package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test1 {
    @Test
    @DisplayName("Работа с аргументом - пустой строкой")
    void nullTest() {
        // given
        String firstTest = "";
        // then
        int a = Task1.minutesToSeconds(firstTest);
        assertThat(a).isEqualTo(-1);


    }

    @Test
    @DisplayName("Вывод -1 при некорректной строке")
    void incorrectStringsTest() {
        // given
        String[] incorrectStrings={"5" , "3:150" , "aa:bb" , "45:34:52","-97: 53", "3 : -07","f:07"};
        int[] result = new int[7];
        // when
        for (int i = 0; i < 7; i++) result[i] = Task1.minutesToSeconds(incorrectStrings[i]);
        //then
        assertThat(result).containsExactly(-1, -1, -1, -1, -1, -1, -1);
    }

    @Test
    @DisplayName("Расчет длины видео для корректных строк")
    void simpleStringsTest() {
        // given
        String[] correctStrings={"55:30" , "183934:59" , "7:018" , "  1  :   8"};
        int[] result = new int[4];
        // when
        for (int i = 0; i < 4; i++) result[i] = Task1.minutesToSeconds(correctStrings[i]);
        //then
        assertThat(result).containsExactly(3330, 11036099, 438, 68);
    }
}

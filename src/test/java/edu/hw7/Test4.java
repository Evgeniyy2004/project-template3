package edu.hw7;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw7.Task4.countByOne;
import static edu.hw7.Task4.countByThreads;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class Test4 {

    @Test
    @DisplayName("Количество итераций может быть только положительным")
    void illegalException() {
        try {
            countByOne(0);
            fail("Количество точек может быть только положительным");
        } catch (IllegalArgumentException e) {
        }

        try {
            Task4.countByThreads(0);
            fail("Количество точек может быть только положительным");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    @DisplayName("Приближенное значение π, полученное многопоточным и однопоточным алгоритм, должно быть одинаковым")
    void equal() {
        //Arrange
        var argument = 17261;

        //Act
        var result1 = countByOne(argument);
        var result2 = countByOne(argument);

        //Assert
        var expected = 3.17942;
        assertThat(Math.abs(result1 - expected)).isLessThan(1e-1);
        assertThat(Math.abs(result2 - expected)).isLessThan(1e-1);
        assertThat(Math.abs(result1 - result2)).isLessThan(1e-1);
    }

    @Test
    @DisplayName("Многопоточная версия должна работать быстрее однопоточной")
    void faster() {
        //Arrange
        var number = new Random().nextInt(10000, 100000);

        //Act
        long startTime = System.nanoTime();
        countByOne(number);
        long endTime = System.nanoTime();
        var duration = endTime - startTime;

        long startTime1 = System.nanoTime();
        countByThreads(number);
        long endTime1 = System.nanoTime();
        var duration1 = endTime1 - startTime1;

        //Assert
        assertThat(duration1).isLessThan(duration);
    }

}

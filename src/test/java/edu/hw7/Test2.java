package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class Test2 {

    @Test
    @DisplayName("Факториала отрицательного числа не существует")
    void illegalException() {
        try {
            Task2.factorial(-6L);
            fail("Нельзя получить факториал отрицательного числа");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    @DisplayName("Крайний случай - факториал нуля равен единице")
    void edgeSituation() {
        //Arrange
        var argument = 0L;

        //Act
        var result = Task2.factorial(argument);

        //Assert
        assertThat(result).isEqualTo(1L);
    }

    @Test
    @DisplayName("Результат должен соответствовать реальному факториалу аргумента")
    void standartData() {
        //Arrange
        var argument = (new Random().nextLong(16));
        var expected = 1L;
        for (long y = 2; y <= argument; y++) {
            expected *= y;
        }

        //Act
        var result = Task2.factorial(argument);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

}

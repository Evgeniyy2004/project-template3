package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class Test1 {
    @Test
    @DisplayName("Число потоков не может превышать количество ядер процессора или быть отрицательным")
    void illegalException() {
        try {
            Task1.someThreads(Long.valueOf(Runtime.getRuntime().availableProcessors() + 1));
            fail("Число потоков не может превышать количество ядер процессора");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            Task1.someThreads(-1L);
            fail("Число потоков не может быть отрицательным");
        } catch (IllegalArgumentException ignored) {
        }

    }

    @Test
    void normalData() {
        //Arrange
        var argument = Long.valueOf(Runtime.getRuntime().availableProcessors() - 1);

        //Act
        var result = Task1.someThreads(argument);

        //Assert
        assertThat(result).isEqualTo(Runtime.getRuntime().availableProcessors() - 1);

    }

}

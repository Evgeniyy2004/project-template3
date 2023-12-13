package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task1.averageTime;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test1 {
    @Test
    @DisplayName("Пример с сайта")
    void countTime() {
        //Arrange
        String [] all = new String[]{"2022-03-12, 20:20 - 2022-03-12, 23:50","2022-04-01, 21:30 - 2022-04-02, 01:20"};

        //Act
        var result = averageTime(all);

        //Assert
        assertThat(result.toHours()).isEqualTo(3);
        assertThat(result.toMinutes()%60).isEqualTo(40);
    }

    @Test
    @DisplayName("Пробелы не влияют на перевод строки в формат LocalDateTime")
    void withoutspaces() {
        //Arrange
        String [] all = new String[]{"2022-03-12,20:20-2022-03-15, 23:50","2022-04-01, 21:30 - 2022-04-02, 01:20"};

        //Act
        var result = averageTime(all);

        //Assert
        assertThat(result.toHours()).isEqualTo(39);
        assertThat(result.toMinutes()%60).isEqualTo(40);
    }

    @Test
    @DisplayName("День, как и положено, длится ровно 24 часа")
    void dayIsEqualTo24() {
        //Arrange
        String [] all = new String[]{"2022-06-12,20:20-2022-06-13, 20:20","2022-04-01, 21:30 - 2022-04-02, 21:30"};

        //Act
        var result = averageTime(all);

        //Assert
        assertThat(result.toHours()).isEqualTo(24);
        assertThat(result.toMinutes()%60).isEqualTo(0);
    }
}

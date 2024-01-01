package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static edu.hw5.Task3.oneOfFormats;
import static org.assertj.core.api.Assertions.assertThat;

public class Test3 {
    @Test
    @DisplayName("Метод работает со всеми форматами, предложенными на сайте")
    void all() {
        var one = oneOfFormats("2020 -10 - 11");
        assertThat(one.get()).isEqualTo(LocalDate.of(2020, 10, 11));

        one = oneOfFormats("1/3/1978");
        assertThat(one.get()).isEqualTo(LocalDate.of(1978, 3, 1));

        one = oneOfFormats("1/10/20");
        assertThat(one.get()).isEqualTo(LocalDate.of(2020, 10, 1));

        one = oneOfFormats("yesterday");
        assertThat(one.get()).isEqualTo(LocalDate.now().minusDays(1));

        one = oneOfFormats("today");
        assertThat(one.get()).isEqualTo(LocalDate.now());

        one = oneOfFormats("tomorrow");
        assertThat(one.get()).isEqualTo(LocalDate.now().plusDays(1));

        one = oneOfFormats("   200 days ago  ");
        assertThat(one.get()).isEqualTo(LocalDate.now().plusDays(-200));
    }
}

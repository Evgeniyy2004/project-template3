package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test6 {
    @Test
    @DisplayName("Добавление акций уже существующей компании и удаление несуществующей акции не приводят к исключению")
    void noExceptionsTest() {
        var market = new Task6();
        try {
            market.mostValuableStock();
            assertTrue(false);
        } catch (RuntimeException exception) {
            assertTrue(true);
        }
        market.add(new Stock("NVIDIA", 4));
        market.add(new Stock("NVIDIA", 8));
        market.add(new Stock("Yandex", 2));
        var finalStock = new Stock("EE", 11);
        market.add(finalStock);
        market.add(new Stock("EE", 10));
        assertThat(market.mostValuableStock().price).isEqualTo(10);
        market.remove(finalStock); //nothing happens
        assertThat(market.mostValuableStock().price).isEqualTo(10);
    }

    @Test
    @DisplayName("Проверка нахождения самой дорогой акции.")
    void isQueueSorted() {
        var market = new Task6();
        market.add(new Stock("NotNVIDIA", 8));
        market.add(new Stock("NVIDIA", 457));
        market.add(new Stock("Yandex", 28));
        market.add(new Stock("Tyndex", 290));
        assertThat(market.mostValuableStock().price).isEqualTo(457);
    }
}

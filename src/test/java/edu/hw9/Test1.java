package edu.hw9;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test1 {

    @Test
    @DisplayName("Тест генерирует случайное количество метрик. Каждая метрика содержит случайное количество данных.")
    void randomData() {

        //Arrange
        var number = new Random().nextInt(1,10000);
        var pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletableFuture[] all = new CompletableFuture[number];

        //Act
        for(int j = 1; j <= number; j++) {
            int finalJ = j;
            all[j-1]= CompletableFuture.runAsync(()->{
                var random =ThreadLocalRandom.current();
                var count = random.nextInt(1000);
                var data = random.doubles().limit(count);
                Task1.stats("metric"+ finalJ,data.toArray());
            },pool);
        }
        CompletableFuture.allOf(all).join();
        pool.shutdown();
        var result = Task1.collect();

        //Assert
        for(int y  =  1; y <= number; y++) {
            assertNotNull(result.get("metric" + y));
            assertTrue(result.get("metric"+y).size() == 4 || result.get("metric"+y).size() == 2);
        }
    }
}

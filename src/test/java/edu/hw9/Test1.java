package edu.hw9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Test1 {

    @Test
    @DisplayName("Тест генерирует случайное количество метрик. Каждая метрика содержит случайное количество данных.")
    void randomData() {

        var number = new Random().nextInt(1,100000);
        var pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletableFuture[] all = new CompletableFuture[number];

        for(int j = 0; j < number; j++) {
            int finalJ = j;
            all[j]= CompletableFuture.runAsync(()->{
                var random =ThreadLocalRandom.current();
                var count = random.nextInt(1000);
                var data = random.doubles(count);
                Task1.stats("metric"+ finalJ,data.toArray());
            },pool);
        }
        CompletableFuture.allOf(all).join();
        pool.shutdown();
        var result = Task1.collect();
        return;
    }
}

package edu.hw7;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

public class Task1 {

    private Task1() {
    }

    public static Long someThreads(long numberOfThreads) {
        if (numberOfThreads > Runtime.getRuntime().availableProcessors() || numberOfThreads < 0) {
            throw new IllegalArgumentException();
        }
        CompletableFuture[] all = new CompletableFuture[(int) numberOfThreads];
        var value = new AtomicLong(0L);
        for (int j = 0; j < numberOfThreads; j++) {
            all[j] = CompletableFuture.runAsync(new Thread(() -> value.addAndGet(1)));

        }
        CompletableFuture.allOf(all).join();
        return value.get();
    }
}

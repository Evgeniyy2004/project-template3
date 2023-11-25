package edu.hw7;

import java.util.concurrent.atomic.AtomicLong;

public class Task1 {

    private Task1() {
    }

    public static Long someThreads(Long numberOfThreads) {
        if (numberOfThreads > Runtime.getRuntime().availableProcessors() || numberOfThreads < 0) {
            throw new IllegalArgumentException();
        }
        var value = new AtomicLong(0L);
        for (long j = 0; j < numberOfThreads; j++) {
            var thread = new Thread(() -> value.addAndGet(1));
            thread.start();
        }
        while (value.get() != numberOfThreads) {

        }
        return value.get();
    }
}

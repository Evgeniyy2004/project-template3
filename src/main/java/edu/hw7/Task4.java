package edu.hw7;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("RegexpSingleline")
public class Task4 {
    private static int circleCount = 0;
    private static int totalCount = 0;
    public static double countByOne(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        int totalCount = 0;
        int circleCount = 0;
        for (int u = 0; u < N; u++) {
            totalCount++;
            double rngx = new Random().nextDouble() * 2 - 1;
            double rngy = new Random().nextDouble() * 2 - 1;
            if (Math.sqrt(rngx * rngx + rngy * rngy) <= 1) {
                circleCount++;
            }
        }
        return 4 * (((double) circleCount) / ((double) totalCount));
    }

    public static double countByThreads(int N) {
        circleCount = 0;
        totalCount = 0;
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        var processors = Runtime.getRuntime().availableProcessors();
        long startTime = System.nanoTime();
        countByOne(N);
        long endTime = System.nanoTime();
        var oneThreadDuration = (endTime - startTime);

        long startTime1 = System.nanoTime();
        Runnable task = (() -> {
            for (int i = 0; i < Math.min(N - totalCount, N/processors); i++) {
                totalCount++;
                var curr = ThreadLocalRandom.current().nextDouble(-1, 1 + 1e-6);
                var curr1 = ThreadLocalRandom.current().nextDouble(-1, 1 + 1e-6);
                var d = Math.sqrt(curr * curr + curr1 * curr1);
                if (d <= 1) {
                    circleCount++;
                }
            }
        });
        try (var executor = Executors.newCachedThreadPool()) {
            while (totalCount < N) {
                executor.execute(task);
            }
        }
        long endTime1 = System.nanoTime();
        var multiThreadDuration = (endTime1 - startTime1);

        System.out.printf("Выполнение однопоточным методом:  %d ms%n", oneThreadDuration);
        System.out.printf("Выполнение многопоточным методом: %d ms%n", multiThreadDuration);

        System.out.printf("Погрешность: %f %n", Math.abs(4 * (((double) circleCount) / ((double) N)) - Math.PI));
        return 4 * (((double) circleCount) / ((double) N));
    }
}

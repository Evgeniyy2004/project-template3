package edu.hw7;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Task4 {
    private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static int totalCount = 0;

    private static final double ACCURACY = 1e-6;

    private Task4() {
    }

    public static double countByOne(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        totalCount = 0;
        int circleCount = 0;
        for (int u = 0; u < n; u++) {
            totalCount++;
            double rngx = new Random().nextDouble() * 2 - 1;
            double rngy = new Random().nextDouble() * 2 - 1;
            if (Math.sqrt(rngx * rngx + rngy * rngy) <= 1) {
                circleCount++;
            }
        }
        return 2 * 2 * (((double) circleCount) / ((double) totalCount));
    }

    public static double countByThreads(int n) {
        int circleCount = 0;
        totalCount = 0;
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        /*long startTime = System.nanoTime();
        countByOne(N);
        long endTime = System.nanoTime();
        var oneThreadDuration = (endTime - startTime);*/

        /*long startTime1 = System.nanoTime();*/
        Callable task = (() -> {
            var circles = 0;
            for (int i = 0; i < Math.min(n - totalCount, n / PROCESSORS); i++) {
                var curr = ThreadLocalRandom.current().nextDouble(-1, 1 + ACCURACY);
                var curr1 = ThreadLocalRandom.current().nextDouble(-1, 1 + ACCURACY);
                var d = Math.sqrt(curr * curr + curr1 * curr1);
                if (d <= 1) {
                    circles++;
                }
            }
            return circles;
        });
        try (var executor = Executors.newFixedThreadPool(PROCESSORS)) {
            while (totalCount < n) {
                circleCount += (int) executor.submit(task).get();
                totalCount += Math.min(n - totalCount, n / PROCESSORS);
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 2 * 2 * (((double) circleCount) / ((double) n));
    }
}

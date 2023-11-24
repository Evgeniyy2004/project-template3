package edu.hw7;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("RegexpSingleline")
public class Task4 {
    public static double countByOne(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        int totalCount = 0;
        int circleCount = 0;
        for (int u = 0;  u < N; u++) {
            totalCount++;
            double rngx = new Random().nextDouble() * 2 - 1;
            double rngy = new Random().nextDouble()*2 - 1;
            if (Math.sqrt(rngx*rngx + rngy*rngy) <= 1) {
                circleCount++;
            }
        }
        return 4*(((double) circleCount)/((double) totalCount));
    }

    public static double countByThreads(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        var processors = Runtime.getRuntime().availableProcessors();
        long startTime = System.nanoTime();
        countByOne(N);
        long endTime = System.nanoTime();
        var oneThreadDuration = (endTime - startTime);

        long startTime1 = System.nanoTime();
        int totalCount = 0;
        AtomicInteger circleCount = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(processors);
        while (totalCount < N) {
            totalCount++;
            executor.submit(() -> {
                var curr = ThreadLocalRandom.current().doubles(2, -1, 1 + 1e-6).toArray();
                var d = Math.sqrt(curr[0] * curr[0] + curr[1] * curr[1]);
                if (d <= 1) circleCount.addAndGet(1);
            });
        }
        executor.shutdown();
        long endTime1 = System.nanoTime();
        var multiThreadDuration = (endTime1 - startTime1);

        System.out.printf("Выполнение однопоточным методом:  %d ms%n",oneThreadDuration);
        System.out.printf("Выполнение многопоточным методом: %d ms%n",multiThreadDuration);

        System.out.printf("Погрешность: %f %n", Math.abs(4*(((double) circleCount.get())/((double) N))- Math.PI));
        return 4*(((double) circleCount.get())/((double) N));
    }
}

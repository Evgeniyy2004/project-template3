package edu.hw8;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task2 {

    @SuppressWarnings("")
    public static void fibonacci(int[] n) {
        var notYet = new PoolRealisation();
        notYet.create(n.length);
        for (int i = 0; i < n.length; i++) {
            int finalI = i;
            notYet.execute(() -> {
                if (n[finalI] == 0) {
                    log.info("Нулевое число Фибоначчи равно 0");
                } else if (n[finalI] == 1) {
                    log.info("Первое число Фибоначчи равно 1");
                } else {
                    int x = 0;
                    int y = 1;
                    for (int k = 2; k <= n[finalI]; k++) {
                        var temp = x + y;
                        x = y;
                        y = temp;
                    }
                    log.info(String.format("%d-е число Фибоначчи равно %d", n[finalI], y));
                }
            });
        }
    }
}

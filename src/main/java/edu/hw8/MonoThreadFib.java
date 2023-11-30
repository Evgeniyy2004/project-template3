package edu.hw8;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MonoThreadFib {
    public static int count(int n) {
            if (n == 0) {
                log.info("Нулевое число Фибоначчи равно 0");
                return 0;
            } else if (n == 1) {
                log.info("Первое число Фибоначчи равно 1");
                return 1;
            } else {
                int x = 0;
                int y = 1;
                for (int k = 2; k <= n; k++) {
                    var temp = x + y;
                    x = y;
                    y = temp;
                }
                log.info(String.format("%d-е число Фибоначчи равно %d", n, y));
                return y;
            }
    }
}

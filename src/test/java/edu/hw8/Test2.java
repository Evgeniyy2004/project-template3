package edu.hw8;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class Test2 {

    @Test
    void checkPool() {
        int n = new Random().nextInt(10,100);
        int [] all = new int[n];
        for (int y = 0; y< n; y++) all[y] = new Random().nextInt(10,50);
        var notYet = new PoolRealisation();
        notYet.create(n);
        for (int i = 0; i < n; i++) {
            int finalI = i;
            notYet.execute(() -> {
                if (all[finalI] == 0) {
                    log.info("Нулевое число Фибоначчи равно 0");
                } else if (all[finalI] == 1) {
                    log.info("Первое число Фибоначчи равно 1");
                } else {
                    int x = 0;
                    int y = 1;
                    for (int k = 2; k <= all[finalI]; k++) {
                        var temp = x + y;
                        x = y;
                        y = temp;
                    }
                    log.info(String.format("%d-е число Фибоначчи равно %d", all[finalI], y));
                    assertThat(y).isEqualTo(MonoThreadFib.count(all[finalI]));
                }
            });
        }
    }
}

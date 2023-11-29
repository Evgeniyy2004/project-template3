package edu.hw8;

import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Test2 {

    @Test
    void checkPoolSpeed() {
        int n = new Random().nextInt(10,100);
        int [] all = new int[n];
        for (int y = 0; y< n; y++) all[y] = new Random().nextInt(10,50);
        var start = System.currentTimeMillis();
        Task2.fibonacci(all);
        var end = System.currentTimeMillis();
        var start1 = System.currentTimeMillis();
        MonoThreadFib.count(all);
        var end1 = System.currentTimeMillis();
        assertThat(end - start).isLessThanOrEqualTo(end1 - start1);
    }
}

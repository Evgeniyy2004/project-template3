package edu.hw7;

import java.util.List;
import java.util.Vector;

public class Task2 {

    private Task2() {
    }

    public static Long factorial(Long number) {
        if (number == 0) {
            return 1L;
        }
        if (number < 0) {
            throw new IllegalArgumentException();
        }
        List<Long> before = new Vector<>();
        for (long j = 1; j <= number; j++) {
            before.add(j);
        }
        return before.parallelStream().reduce(1L, Math::multiplyExact);
    }
}

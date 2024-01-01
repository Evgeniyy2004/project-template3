package edu.hw10;

import java.util.Arrays;

public interface MethodsForHandling {
    @Cache(persist = false)
    default long sum(int i) {
        if (i == 0) {
            return 0;
        }
        return i + sum(i - 1);
    }

    @Cache(persist = true)
    default int sieveOfEratosthenes(int n, int notInclude) {
        boolean[] all = new boolean[n + 1];
        Arrays.fill(all, true);
        all[0] = false;
        all[1] = false;
        for (int u = 2; u < all.length; u++) {
            if (all[u]) {
                for (int x = u * u; x < all.length; x += u) {
                    all[x] = false;
                }
            }
        }
        int res = 0;
        for (int y = 0; y < all.length; y++) {
            if (all[y] && y != notInclude) {
                res++;
            }
        }
        return res;
    }
}

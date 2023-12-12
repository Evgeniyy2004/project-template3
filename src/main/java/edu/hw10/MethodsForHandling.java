package edu.hw10;

import java.util.Arrays;
import java.util.stream.Collectors;

public interface MethodsForHandling {


    public default long sum(int i) {
        if(i ==0) return 0;
        return i + sum(i-1);
    }

    public default long fib(int n) {
        if (n==0) return 0;
        if (n == 1) return 1;
        else return fib(n-1)+fib(n-2);
    }

    public default int sieveOfEratosthenes(int n, int[] notInclude) {
        var  skip = Arrays.stream(notInclude).boxed().collect(Collectors.toSet());
        boolean [] all = new boolean[n+1];
        Arrays.fill(all, true);
        all[0] = false;
        all[1] =  false;
        for(int u = 2; u < all.length; u++) {
            if (all[u]) {
                for(int x = u*u; x < all.length; x+=u) {
                    all[u] = false;
                }
            }
        }
        int res = 0;
        for(int y =0; y < all.length; y++) {
            if (all[y] && !skip.contains(y)) res++;
        }
        return res;
    }
}

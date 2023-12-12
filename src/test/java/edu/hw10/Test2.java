package edu.hw10;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Proxy;
import java.util.concurrent.ThreadLocalRandom;

public class Test2 {
    @Test
    void testWithDisk() {
        for(int i = 0; i < 100; i++) {
            var proxy =  (MethodsForHandling) Proxy.newProxyInstance(MethodsForHandling.class.getClassLoader()
                ,MethodsForHandling.class.getInterfaces(), new Handler(new Implementor()));
            proxy.fib(ThreadLocalRandom.current().nextInt(0));
        }
    }
}

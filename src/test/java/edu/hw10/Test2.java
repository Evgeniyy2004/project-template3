package edu.hw10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Proxy;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Test2 {
    @Test
    @DisplayName("При наличии аннотации Cache прокси Handler в зависимости от параметра persist ищет в кэше данные," +
        " соответствующие текущим или сохраняет аргументы метода и полученное значение в словарь или файл")
    void testWithDisk() {
        var handler = new Handler(new Implementor());
        for(int i = 0; i < 100; i++) {
            var object = new Implementor();
            var proxy =  (MethodsForHandling) Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(), handler);
            var one = new Random().nextInt(1,500);
            var args = new Random().nextInt(one);
            proxy.sieveOfEratosthenes(one, args);
        }

    }

    @Test
    @DisplayName("При наличии аннотации Cache прокси Handler в зависимости от параметра persist ищет в кэше данные," +
        " соответствующие текущим или сохраняет аргументы метода и полученное значение в словарь или файл")
    void testWithMap() {
        var handler = new Handler(new Implementor());
        for(int i = 0; i < 1000; i++) {
            var object = new Implementor();
            var proxy =  (MethodsForHandling) Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(), handler);
            proxy.sum(ThreadLocalRandom.current().nextInt(100));
            var one = new Random().nextInt(1,1000);
            proxy.sum(one);
        }
    }
}

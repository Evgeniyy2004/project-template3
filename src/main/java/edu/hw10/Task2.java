package edu.hw10;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class Task2 implements InvocationHandler {

    ConcurrentHashMap<Method, ConcurrentHashMap<Object[],Object>> allres = new ConcurrentHashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (allres.containsKey(method)) {
            if (allres.get(method).containsKey(args)) return allres.get(method).get(args);
            else allres.get(method).put(args, method.invoke(proxy, args));
            return allres.get(method).get(args);
        }
        allres.put(method, new ConcurrentHashMap<>());
        allres.get(method).put(args,method.invoke(proxy,args));
        return allres.get(method).get(args);
    }
}

package edu.hw10;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Handler implements InvocationHandler {
    ConcurrentHashMap<Method, HashMap<Object[],Object>> all = new ConcurrentHashMap<>();

    private Object invokator;

    public Handler(Object i) {

        this.invokator = i;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            if (!method.getAnnotationsByType(Cache.class)[0].persist()) {
                if (all.containsKey(method) && all.get(method).containsKey(args)) {
                    return all.get(method).get(args);
                }
                var res = method.invoke(invokator, args);
                if (all.containsKey(method)) all.get(method).put(args,res);
                else {
                    all.put(method, new HashMap<>());
                    all.get(method).put(args,res);
                }
                return res;
            }
            else {
                var futureName = String.valueOf(invokator.getClass());
                if(!Files.exists(new File(futureName).toPath())) {
                    Files.createTempDirectory(futureName);
                }
                var list = new File(futureName).listFiles();
                var necessary = Arrays.stream(list).filter(r->r.getName().equals(method.getName()+".txt"));
                if (necessary.count() ==0) {
                    necessary.close();
                    File.cre
                }
            }
        }
        return method.invoke(invokator,args);
    }
}

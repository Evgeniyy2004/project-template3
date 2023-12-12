package edu.hw10;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
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
                if (necessary.findAny().isEmpty()) {
                    necessary.close();
                    File.createTempFile(method.getName(),"txt",new File(futureName));
                }
            }
        }
        return method.invoke(invokator,args);
    }

    public Object findOrDIY(File file, Object[] args, Method m)
        throws IOException, InvocationTargetException, IllegalAccessException {
            var lines = Files.readAllLines(file.toPath());
            var stringversion = new String[args.length];
            for (int u =0; u < args.length; u++) {
                stringversion[u]=String.valueOf(args[u]);
            }
            for (int i =0; i < lines.size(); i++) {
                var curr = lines.get(i).split(" ");
                var value = curr[1];
                var arguments = Arrays.stream(curr[0].split(",")).filter(r->r!="").toArray();
                var array = new String[(int)arguments.length];
                for(int y = 0; y < arguments.length; y++) {
                    array[y] = (String) arguments[y];
                }
                if (Arrays.equals(stringversion, array)) {
                    var clazz = m.getReturnType();
                    if( Boolean.class == clazz ) return Boolean.parseBoolean( value );
                    if( Byte.class == clazz ) return Byte.parseByte( value );
                    if( Short.class == clazz ) return Short.parseShort( value );
                    if( Integer.class == clazz ) return Integer.parseInt( value );
                    if( Long.class == clazz ) return Long.parseLong( value );
                    if( Float.class == clazz ) return Float.parseFloat( value );
                    if( Double.class == clazz ) return Double.parseDouble( value );
                }
                return value;
            }
            var write = String.join(",",stringversion);
            var res = m.invoke(invokator,args);
            Files.write(file.toPath(), (write+" "+res+"\n").getBytes());
            return res;
    }
}

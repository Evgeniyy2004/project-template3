package edu.hw10;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class Task1 {

    private Task1() {
    }

    public static Object nextObject(Class c)
        throws Exception {
        var types = c.getDeclaredConstructors()[0].getParameterTypes();
        var parameters = c.getDeclaredConstructors()[0].getParameters();
        var arguments = new Object[types.length];
        for (int i = 0; i < types.length; i++) {
            arguments[i] = getRandomValueForField(types[i], parameters[i]);
        }
        return c.getConstructor(types).newInstance(arguments);
    }


    @SuppressWarnings("ReturnCount")
    private static Object getRandomValueForField(Class parameter, Parameter p) throws Exception {
        Class type = parameter;
        if (type.isEnum()) {
            Object[] enumValues = type.getEnumConstants();
            return enumValues[new Random().nextInt(enumValues.length)];
        } else if (type.equals(Integer.TYPE) || type.equals(Integer.class)) {
            var left = (p.isAnnotationPresent(Min.class)) ? p.getAnnotation(Min.class).value() : Integer.MIN_VALUE;
            var right = (p.isAnnotationPresent(Max.class)) ? p.getAnnotation(Max.class).value() : Integer.MAX_VALUE;
            return new Random().nextInt(left, right);
        } else if (type.equals(Long.TYPE) || type.equals(Long.class)) {
            var left = (p.isAnnotationPresent(Min.class)) ? p.getAnnotation(Min.class).value() : Long.MIN_VALUE;
            var right = (p.isAnnotationPresent(Max.class)) ? p.getAnnotation(Max.class).value() : Long.MAX_VALUE;
            return new Random().nextLong(left, right);
        } else if (type.equals(Double.TYPE) || type.equals(Double.class)) {
            return new Random().nextDouble();
        } else if (type.equals(Float.TYPE) || type.equals(Float.class)) {
            return new Random().nextFloat();
        } else if (type.equals(String.class)) {
            return UUID.randomUUID().toString();
        } else {
            return (char) (new Random().nextInt(2 * 2 * 2 * 2 + 2 * 2 * 2 + 1) + 'A');
        }
    }

    public static Object nextObject(Class c, String method)
        throws Exception {
        var instance = nextObject(c);
        var methods = Arrays.stream(c.getMethods()).filter(r -> r.toString().contains(method)).toArray();
        var parameters = c.getDeclaredConstructors()[0].getParameters();
        var types = ((Method) methods[0]).getParameterTypes();
        var arguments = new Object[types.length];
        for (int i = 0; i < types.length; i++) {
            arguments[i] = getRandomValueForField(types[i], parameters[i]);
        }
        return ((Method) methods[0]).invoke(instance, arguments);
    }
}

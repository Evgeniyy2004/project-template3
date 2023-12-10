package edu.hw10;

import java.lang.reflect.Parameter;
import java.util.Random;
import java.util.UUID;

public class Task1 {
    Object nextObject(Class c)
        throws Exception {
        var types = c.getConstructor().getParameterTypes();
        var parameters = c.getConstructor().getParameters();
        var arguments = new Object[types.length];
        for (int i =0; i < types.length; i++) {
            arguments[i]= getRandomValueForField(types[i], parameters[i]);
        }
        return c.getConstructor(types).newInstance(arguments);
    }
    private Object getRandomValueForField(Class parameter, Parameter p) throws Exception {
        Class type = parameter;

        if(type.isEnum()) {
            Object[] enumValues = type.getEnumConstants();
            return enumValues[new Random().nextInt(enumValues.length)];
        } else if(type.equals(Integer.TYPE) || type.equals(Integer.class)) {
            var left = (p.isAnnotationPresent(Min.class))? p.getAnnotation(Min.class).value(): Integer.MIN_VALUE;
            var right = (p.isAnnotationPresent(Max.class))? p.getAnnotation(Max.class).value(): Integer.MAX_VALUE;
            return new Random().nextInt((int)left,(int)right);
        } else if(type.equals(Long.TYPE) || type.equals(Long.class)) {
            var left = (p.isAnnotationPresent(Min.class))? p.getAnnotation(Min.class).value(): Long.MIN_VALUE;
            var right = (p.isAnnotationPresent(Max.class))? p.getAnnotation(Max.class).value(): Long.MAX_VALUE;
            return new Random().nextLong((long) left,(long) right);
        } else if(type.equals(Double.TYPE) || type.equals(Double.class)) {
            var left = (p.isAnnotationPresent(Min.class))? p.getAnnotation(Min.class).value(): Double.MIN_VALUE;
            var right = (p.isAnnotationPresent(Max.class))? p.getAnnotation(Max.class).value(): Double.MAX_VALUE;
            return new Random().nextDouble((double) left, (double) right);
        } else if(type.equals(Float.TYPE) || type.equals(Float.class)) {
            var left = (p.isAnnotationPresent(Min.class))? p.getAnnotation(Min.class).value(): Float.MIN_VALUE;
            var right = (p.isAnnotationPresent(Max.class))? p.getAnnotation(Max.class).value(): Float.MAX_VALUE;
            return new Random().nextFloat((float) left, (float) right);
        } else if(type.equals(String.class)) {
            return UUID.randomUUID().toString();
        } else {
            return (char)(new Random().nextInt(26)+'A');
        }
    }

    Object nextObject(Class c, String method)
        throws Exception {
        var instance = c.newInstance();
        var types = new Class [c.getMethod(method).getParameterTypes().length];
        var parameters = c.getConstructor().getParameters();
        var arguments = new Object[types.length];
        for (int i =0; i < types.length; i++) {
            arguments[i]= getRandomValueForField(types[i], parameters[i]);
        }
        return c.getMethod(method).invoke(instance,arguments);
    }
}

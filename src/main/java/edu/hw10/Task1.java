package edu.hw10;

import java.util.Random;
import java.util.UUID;

public class Task1 {
    Object nextObject(Class c)
        throws Exception {
        var instance = c.newInstance();
        var types = new Class [c.getConstructor().getParameterTypes().length];
        var arguments = new Object[types.length];
        for (int i =0; i < types.length; i++) {
            /*field.setAccessible(true);
            Object value = getRandomValueForField(field);
            field.set(instance, value);*/
            arguments[i]= getRandomValueForField(types[i]);
        }
        return c.getConstructor(types).newInstance(arguments);
    }
    private Object getRandomValueForField(Class parameter) throws Exception {
        Class type = parameter;
        // Note that we must handle the different types here! This is just an
        // example, so this list is not complete! Adapt this to your needs!
        if(type.isEnum()) {
            Object[] enumValues = type.getEnumConstants();
            return enumValues[new Random().nextInt(enumValues.length)];
        } else if(type.equals(Integer.TYPE) || type.equals(Integer.class)) {
            return new Random().nextInt();
        } else if(type.equals(Long.TYPE) || type.equals(Long.class)) {
            return new Random().nextLong();
        } else if(type.equals(Double.TYPE) || type.equals(Double.class)) {
            return new Random().nextDouble();
        } else if(type.equals(Float.TYPE) || type.equals(Float.class)) {
            return new Random().nextFloat();
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
        var arguments = new Object[types.length];
        for (int i =0; i < types.length; i++) {
            /*field.setAccessible(true);
            Object value = getRandomValueForField(field);
            field.set(instance, value);*/
            arguments[i]= getRandomValueForField(types[i]);
        }
        return c.getMethod(method).invoke(instance,arguments);
    }
}

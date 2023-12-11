package edu.hw10;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.fail;

public class Test1 {

    @Test
    void randomDataForMethod() {
        for (int i = 0; i < 10000; i++) {
            try {
                var methodName = "create";
                NeedToGenerate now = (NeedToGenerate) Task1.nextObject(NeedToGenerate.class, methodName);
                var methods = Arrays.stream(NeedToGenerate.class.getMethods()).filter(r->r.toString().contains(methodName)).toArray();
                var types = ((Method)methods[0]).getParameterTypes();
                var annotation = (Min) NeedToGenerate.class.getMethod(methodName,types).getParameters()[0].getAnnotations()[0];
                var annotation1 = (Max) NeedToGenerate.class.getMethod(methodName,types).getParameters()[0].getAnnotations()[1];
                if (now.a < annotation.value() || now.a >= annotation1.value()) {
                    fail("Value of field a doesnt match annotation");
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    @Test
    void randomDataForConstructor() {
        for (int i = 0; i < 10000; i++) {
            try {
                NeedToGenerate now = (NeedToGenerate) Task1.nextObject(NeedToGenerate.class);
                var annotation = (Min) NeedToGenerate.class.getConstructors()[0].getParameters()[0].getAnnotations()[0];
                var annotation1 = (Max) NeedToGenerate.class.getConstructors()[0].getParameters()[0].getAnnotations()[1];
                if (now.a < annotation.value() || now.a >= annotation1.value()) {
                    fail("Value of field a doesnt match annotation");
                }
            } catch (Exception e) {
                fail();
            }
        }
    }
}

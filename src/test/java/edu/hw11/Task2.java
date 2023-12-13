package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import edu.hw1.ClassForDelegate;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.returns;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2 {
    @Test
    void redefine()  {
        TypeDescription typeDescription = TypePool.Default.ofSystemLoader()
            .describe("edu.hw1.Task1")
            .resolve();
        var b = new ByteBuddy()
            .redefine(typeDescription, ClassFileLocator.ForClassLoader.ofSystemLoader())
            .method(returns(Task1.class)).intercept(MethodDelegation.to(ClassForDelegate.class))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION).getLoaded();

        try {
            assertThat(b.getMethod(edu.hw1.Task1.class.getMethods()[0].getName(), String.class)
                .invoke(new Task1(), "23:50")).isEqualTo(4);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
           e.printStackTrace();
        }
    }
}

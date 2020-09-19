package next.reflection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Junit3TestRunner {
    private static final Logger logger = LoggerFactory.getLogger(Junit3TestRunner.class);

    @Test
    public void run() throws Exception {
        Class<?> clazz = Junit3Test.class;
        Object o = clazz.getConstructor().newInstance();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().startsWith("test")) {
                method.invoke(o);
            }
        }

    }
}

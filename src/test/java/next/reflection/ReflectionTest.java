package next.reflection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() {
        Class<Question> clazz = Question.class;
        logger.debug(clazz.getName());

        logger.debug("----------------------constructor--------------------------");
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            logger.debug(constructor.getName());
            logger.debug(String.valueOf(constructor.getModifiers()));

            for (Class<?> type : constructor.getParameterTypes()) {
                logger.debug(type.getName());
            }
        }

        logger.debug("----------------------field--------------------------");
        for (Field field : clazz.getDeclaredFields()) {
            logger.debug(field.getName());
            logger.debug(String.valueOf(field.getModifiers()));
        }

        logger.debug("----------------------method--------------------------");
        for (Method method : clazz.getDeclaredMethods()) {
            logger.debug(method.getName());
            logger.debug(String.valueOf(method.getModifiers()));

            for (Class<?> type : method.getParameterTypes()) {
                logger.debug(type.getName());
            }
        }
    }

    @Test
    public void privateFieldAccess() throws Exception {
        Class<?> clazz = Student.class;
        Object o = clazz.getConstructor().newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equals("name")) {
                field.setAccessible(true);
                field.set(o, "SANGCO");
            }

            if (field.getName().equals("age")) {
                field.setAccessible(true);
                field.set(o, 30);
            }
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (isGetter(method)) {
                if (int.class.equals(method.getReturnType())) {
                    logger.debug(String.valueOf((Integer) method.invoke(o)));
                }
                if (String.class.equals(method.getReturnType())) {
                    logger.debug((String) method.invoke(o));
                }
            }
        }

    }

    public static boolean isGetter(Method method){
        if (!method.getName().startsWith("get")) return false;
        if (method.getParameterTypes().length != 0) return false;
        if (void.class.equals(method.getReturnType())) return false;
        return true;
    }

}

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


}

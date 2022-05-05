package com.xuesran.tdd.chapter01;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

/**
 * The type Args.
 *
 * @author xueshun
 */
public class Args {
    /**
     * Parse t.
     *
     * @param <T>          the type parameter
     * @param optionsClass the options class
     * @param args         the args
     * @return the t
     */
    public static <T> T parse(Class<T> optionsClass, String... args) {
        try {
            Constructor<?> constructor = optionsClass.getDeclaredConstructors()[0];
            constructor.setAccessible(Boolean.TRUE);
            Parameter parameter = constructor.getParameters()[0];
            Option option = parameter.getAnnotation(Option.class);
            List<String> arguments = Arrays.asList(args);
            return (T) constructor.newInstance(arguments.contains("-" + option.value()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

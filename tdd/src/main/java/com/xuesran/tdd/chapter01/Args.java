package com.xuesran.tdd.chapter01;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
        Constructor<?> constructor = optionsClass.getDeclaredConstructors()[0];
        try {
            constructor.setAccessible(Boolean.TRUE);
            return (T) constructor.newInstance(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

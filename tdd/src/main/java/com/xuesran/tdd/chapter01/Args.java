package com.xuesran.tdd.chapter01;

import java.lang.reflect.Constructor;
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

            List<String> arguments = Arrays.asList(args);
            Object[] values = Arrays.stream(constructor.getParameters()).map(it -> parseOption(arguments, it)).toArray();
            return (T) constructor.newInstance(values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object parseOption(List<String> arguments, Parameter parameter) {
        Object value = null;
        Option option = parameter.getAnnotation(Option.class);
        if (parameter.getType() == boolean.class) {
            value = new BooleanParse().parse(arguments, option);
        }
        if (parameter.getType() == int.class) {
            value = new IntParse().parse(arguments, option);
        }
        if (parameter.getType() == String.class) {
            value = new StringParse().parse(arguments, option);
        }
        return value;
    }

    /**
     * The interface Option parse.
     */
    interface OptionParse {
        /**
         * Parse object.
         *
         * @param arguments the arguments
         * @param option    the option
         * @return the object
         */
        Object parse(List<String> arguments, Option option);
    }

    static class BooleanParse implements OptionParse {
        @Override
        public Object parse(List<String> arguments, Option option) {
            return arguments.contains("-" + option.value());
        }
    }

    static class StringParse implements OptionParse {
        @Override
        public Object parse(List<String> arguments, Option option) {
            int index = arguments.indexOf("-" + option.value());
            return arguments.get(index + 1);
        }
    }

    static class IntParse implements OptionParse {

        @Override
        public Object parse(List<String> arguments, Option option) {
            int index = arguments.indexOf("-" + option.value());
            return Integer.parseInt(arguments.get(index + 1));
        }
    }

}
